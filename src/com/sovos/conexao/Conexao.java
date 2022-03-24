package com.sovos.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    Connection conexao = null;

    public Conexao(){
    }

    public Connection getNewConecction() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicio17_03", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexao;
    }
}
