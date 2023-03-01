package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Conta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CriarContaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O código da agência deve ser preenchido!")
    private String codigoAgencia;
    @Pattern(regexp = "^[0-9]{5}$", message = "Numero de conta inválido!")
    private String numeroConta;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8}$", message = "Senha inválida!")
    private String senha;
    @NotBlank(message = "O nome deve ser preenchido!")
    private String nome;
    @Pattern(regexp = "^(\\d{3}.\\d{3}.\\d{3}-\\d{2})|(\\d{11})$", message = "CPF inválido!")
    private String cpf;

    public CriarContaDTO(){}

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
