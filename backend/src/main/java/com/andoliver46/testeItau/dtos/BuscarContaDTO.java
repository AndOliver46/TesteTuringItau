package com.andoliver46.testeItau.dtos;

import java.io.Serializable;

public class BuscarContaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String agencia;
    private String conta;
    private String nome;
    private String cpf;

    public BuscarContaDTO(){}

    public BuscarContaDTO(String agencia, String conta, String nome, String cpf) {
        this.agencia = agencia;
        this.conta = conta;
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
