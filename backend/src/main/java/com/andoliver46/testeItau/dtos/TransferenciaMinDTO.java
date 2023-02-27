package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Transferencia;
import com.andoliver46.testeItau.enums.TipoTransferencia;

import java.io.Serializable;
import java.time.Instant;

public class TransferenciaMinDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Instant dataHora;
    private Double valor;
    private TipoTransferencia tipo;
    private Double saldoEmissor;
    private Double saldoReceptor;

    public TransferenciaMinDTO(){
    }

    public TransferenciaMinDTO(Integer id, Instant dataHora, Double valor, String tipo, ContaDTO emissor, ContaDTO receptor) {
        this.id = id;
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = TipoTransferencia.valueOf(tipo);
    }

    public TransferenciaMinDTO(Transferencia entity) {
        this.id = entity.getId();
        this.dataHora = entity.getDataHora();
        this.valor = entity.getValor();
        this.tipo = entity.getTipo();
    }

    public TransferenciaMinDTO(Transferencia entity, Double saldoEmissor, Double saldoReceptor) {
        this.id = entity.getId();
        this.dataHora = entity.getDataHora();
        this.valor = entity.getValor();
        this.tipo = entity.getTipo();
        this.saldoEmissor = saldoEmissor;
        this.saldoReceptor = saldoReceptor;
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

    public Double getSaldoEmissor() {
        return saldoEmissor;
    }

    public void setSaldoEmissor(Double saldoEmissor) {
        this.saldoEmissor = saldoEmissor;
    }

    public Double getSaldoReceptor() {
        return saldoReceptor;
    }

    public void setSaldoReceptor(Double saldoReceptor) {
        this.saldoReceptor = saldoReceptor;
    }
}
