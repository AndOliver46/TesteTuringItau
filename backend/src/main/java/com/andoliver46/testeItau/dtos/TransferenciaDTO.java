package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.entities.Transferencia;
import com.andoliver46.testeItau.enums.TipoTransferencia;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

public class TransferenciaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Instant dataHora;
    private Double valor;
    private TipoTransferencia tipo;

    private ContaDTO emissor;
    private ContaDTO receptor;

    public TransferenciaDTO(){
    }

    public TransferenciaDTO(Integer id, Instant dataHora, Double valor, String tipo, ContaDTO emissor, ContaDTO receptor) {
        this.id = id;
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = TipoTransferencia.valueOf(tipo);
        this.emissor = emissor;
        this.receptor = receptor;
    }

    public TransferenciaDTO(Transferencia entity) {
        this.id = entity.getId();
        this.dataHora = entity.getDataHora();
        this.valor = entity.getValor();
        this.tipo = entity.getTipo();
        this.emissor = new ContaDTO(entity.getEmissor());
        this.receptor = new ContaDTO(entity.getReceptor());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public void setDataHora(Instant dataHora) {
        this.dataHora = dataHora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoTransferencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransferencia tipo) {
        this.tipo = tipo;
    }

    public ContaDTO getEmissor() {
        return emissor;
    }

    public void setEmissor(ContaDTO emissor) {
        this.emissor = emissor;
    }

    public ContaDTO getReceptor() {
        return receptor;
    }

    public void setReceptor(ContaDTO receptor) {
        this.receptor = receptor;
    }
}
