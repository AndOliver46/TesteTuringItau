package com.andoliver46.testeItau.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public class RealizarTransferenciaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "(?:\\.|[0-9])*", message = "Digite somente numeros no valor!")
    private String valor;

    @NotBlank(message = "O tipo de transferencia deve ser preenchido!")
    private String tipo;

    @Pattern(regexp = "^[0-9]{5}$", message = "Numero de conta inválido!")
    private String receptor;

    public RealizarTransferenciaDTO(){
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }
}
