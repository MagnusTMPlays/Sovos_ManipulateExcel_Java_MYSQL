package com.sovos.tabelas;

import java.util.Date;

public class Cliente {

    int id;
    String Nome;
    String CPF;
    Date DtaNascimento;
    String Telefone;

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getDtaNascimento() {
        return DtaNascimento;
    }

    public void setDtaNascimento(Date dtaNascimento) {
        DtaNascimento = dtaNascimento;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }



}
