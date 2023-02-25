package com.andoliver46.testeItau.auth;

import java.io.Serializable;

public class AuthenticationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String numero;
    private String senha;

    public AuthenticationDTO(){
    }

    public AuthenticationDTO(String numero, String senha) {
        this.numero = numero;
        this.senha = senha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
