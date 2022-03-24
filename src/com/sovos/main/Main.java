package com.sovos.main;

import com.sovos.conexao.Conexao;
import com.sovos.tabelas.Cliente;
import com.sovos.tabelas.Empregado;
import com.sovos.tabelas.SelectNotaFiscal;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static final String fileName = "Teste.xlsx";

    public static void main(String[] args) throws IOException {

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        List<Empregado> listEmpregado = new LinkedList<Empregado>();
        List<Cliente> listCliente = new LinkedList<Cliente>();
        List<SelectNotaFiscal> listsNF = new LinkedList<SelectNotaFiscal>();

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheetDados1 = workbook.createSheet("Nota Fiscal 1");
        XSSFSheet sheetDados2 = workbook.createSheet("Nota Fiscal 2");
        XSSFSheet sheetDados3 = workbook.createSheet("Nota Fiscal 3");
        XSSFSheet sheetDados4 = workbook.createSheet("Nota Fiscal 4");
        XSSFSheet sheetDados5 = workbook.createSheet("Nota Fiscal 5");


        int rownum = 0;

        try {
            Conexao c = new Conexao();
            conn = c.getNewConecction();
            stmt = conn.createStatement();


            if (stmt.execute("SELECT \n" +
                    "nf.id as ID_NotaFiscal,\n" +
                    "nf.Serie,\n" +
                    "cl.Nome as Cliente,\n" +
                    "pd.Descrição,\n" +
                    "itnf.QTD,\n" +
                    "pd.Valor as ValorUnit,\n" +
                    "(itnf.qtd * pd.Valor) as ValorTotal\n" +
                    "FROM notafiscal nf , cliente cl ,produto pd ,itensnotafiscal itnf\n" +
                    "WHERE cl.id = nf.Cliente AND nf.id = itnf.idNotaFiscal AND pd.id = itnf.idProdutos;")) {
                rs = stmt.getResultSet();
            }
            int id = 0;
            int newid;
            int qtdID= 0;
            while (rs.next()) {
                SelectNotaFiscal sNF = new SelectNotaFiscal();
                sNF.setID_NotaFiscal(rs.getInt("ID_NotaFiscal"));
                sNF.setSerie(rs.getInt("Serie"));
                sNF.setCliente(rs.getString("Cliente"));
                sNF.setDescrição(rs.getString("Descrição"));
                sNF.setQTD(rs.getInt("QTD"));
                sNF.setValorUnit(rs.getInt("ValorUnit"));
                sNF.setValorTotal(rs.getInt("ValorTotal"));
                newid = sNF.getID_NotaFiscal();
                if (id != newid){
                    qtdID++;
                    id = newid;
                }
                listsNF.add(sNF);
            }

            for (int i = 0; i < qtdID; i++) {
                if (workbook.getNumberOfSheets() < qtdID) {
                    workbook.createSheet("NF" + (i+1));
                }
            }

            int index = 0;

            while (index<qtdID) {

                Row rowCab  =  workbook.getSheetAt(index).createRow(rownum++);

                int cellnumCab = 0;
                double total = 0;

                Cell cellID_NotaFiscal = rowCab.createCell(cellnumCab++);
                Cell cellSerie = rowCab.createCell(cellnumCab++);
                Cell cellCliente = rowCab.createCell(cellnumCab++);
                Cell cellDescrição = rowCab.createCell(cellnumCab++);
                Cell cellQTD = rowCab.createCell(cellnumCab++);
                Cell cellValorUnit = rowCab.createCell(cellnumCab++);
                Cell cellValorTotal = rowCab.createCell(cellnumCab++);
                Cell cellVazio = rowCab.createCell(cellnumCab++);
                Cell cellValorNF = rowCab.createCell(cellnumCab++);


                cellID_NotaFiscal.setCellValue("ID_NotaFiscal");
                cellSerie.setCellValue("Serie");
                cellCliente.setCellValue("Cliente");
                cellDescrição.setCellValue("Descrição");
                cellQTD.setCellValue("QTD");
                cellValorUnit.setCellValue("ValorUnit");
                cellValorTotal.setCellValue("ValorTotal");
                cellVazio.setCellValue("");
                cellValorNF.setCellValue("Valor NF:");
                cellValorNF = rowCab.createCell(cellnumCab++);

                for (SelectNotaFiscal dados : listsNF) {

                    if (dados.getID_NotaFiscal() == (index+1)) {


                        Row row = workbook.getSheetAt(index).createRow(rownum++);
                        int cellnum = 0;


                        cellID_NotaFiscal = row.createCell(cellnum++);
                        cellSerie = row.createCell(cellnum++);
                        cellCliente = row.createCell(cellnum++);
                        cellDescrição = row.createCell(cellnum++);
                        cellQTD = row.createCell(cellnum++);
                        cellValorUnit = row.createCell(cellnum++);
                        cellValorTotal = row.createCell(cellnum++);

                        cellID_NotaFiscal.setCellValue(dados.getID_NotaFiscal());
                        cellSerie.setCellValue(dados.getSerie());
                        cellCliente.setCellValue(dados.getCliente());
                        cellDescrição.setCellValue(dados.getDescrição());
                        cellQTD.setCellValue(dados.getQTD());
                        cellValorUnit.setCellValue(dados.getValorUnit());
                        cellValorTotal.setCellValue(dados.getValorTotal());
                        total+=dados.getValorTotal();

                    }
                }
                cellValorNF.setCellValue(total);
                index++;
                rownum = 0;


           }

            FileOutputStream out = new FileOutputStream(new File(Main.fileName));
            workbook.write(out);
            out.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {

                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {

                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) {

                }
            }
        }
    }
}
