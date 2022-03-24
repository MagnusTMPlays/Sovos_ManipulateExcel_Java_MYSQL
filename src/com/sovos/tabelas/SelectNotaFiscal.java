package com.sovos.tabelas;

public class SelectNotaFiscal {

    int ID_NotaFiscal;
    int Serie;
    String Cliente;
    String Descrição;
    int QTD;
    int ValorUnit;
    int ValorTotal;

    public int getID_NotaFiscal() {
        return ID_NotaFiscal;
    }

    public void setID_NotaFiscal(int ID_NotaFiscal) {
        this.ID_NotaFiscal = ID_NotaFiscal;
    }

    public int getSerie() {
        return Serie;
    }

    public void setSerie(int serie) {
        Serie = serie;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getDescrição() {
        return Descrição;
    }

    public void setDescrição(String descrição) {
        Descrição = descrição;
    }

    public int getQTD() {
        return QTD;
    }

    public void setQTD(int QTD) {
        this.QTD = QTD;
    }

    public int getValorUnit() {
        return ValorUnit;
    }

    public void setValorUnit(int valorUnit) {
        ValorUnit = valorUnit;
    }

    public int getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(int valorTotal) {
        ValorTotal = valorTotal;
    }

    public SelectNotaFiscal() {
    }
}
