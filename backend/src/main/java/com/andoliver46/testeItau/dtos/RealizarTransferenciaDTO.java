package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Transferencia;
import com.andoliver46.testeItau.enums.TipoTransferencia;

import java.io.Serializable;
import java.time.Instant;

public class RealizarTransferenciaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double valor;
    private String tipo;

    private ContaDTO receptor;

    public RealizarTransferenciaDTO(){
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ContaDTO getReceptor() {
        return receptor;
    }

    public void setReceptor(ContaDTO receptor) {
        this.receptor = receptor;
    }
}
