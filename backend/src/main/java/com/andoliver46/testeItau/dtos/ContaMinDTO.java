package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Conta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContaMinDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String numero;
    private Double saldo;

    private AgenciaDTO agencia;

    private List<TransferenciaMinDTO> transferenciasRealizadas = new ArrayList<>();

    private List<TransferenciaMinDTO> transferenciasRecebidas = new ArrayList<>();

    public ContaMinDTO(){}

    public ContaMinDTO(Integer id, String numero, Double saldo, AgenciaDTO agencia, ClienteDTO cliente) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
        this.agencia = agencia;
    }

    public ContaMinDTO(Conta entity) {
        this.id = entity.getId();
        this.numero = entity.getNumero();
        this.saldo = entity.getSaldo();
        this.agencia = new AgenciaDTO(entity.getAgencia());

        entity.getTransferenciasRealizadas().forEach(x -> this.transferenciasRealizadas.add(new TransferenciaMinDTO(x)));
        entity.getTransferenciasRecebidas().forEach(x -> this.transferenciasRecebidas.add(new TransferenciaMinDTO(x)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public AgenciaDTO getAgencia() {
        return agencia;
    }

    public void setAgencia(AgenciaDTO agencia) {
        this.agencia = agencia;
    }

    public List<TransferenciaMinDTO> getTransferenciasRealizadas() {
        return transferenciasRealizadas;
    }

    public List<TransferenciaMinDTO> getTransferenciasRecebidas() {
        return transferenciasRecebidas;
    }
}
