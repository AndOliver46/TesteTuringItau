package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Conta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String numero;
    private Double saldo;

    private AgenciaDTO agencia;
    private ClienteDTO cliente;

    private List<TransferenciaDTO> transferenciasRealizadas = new ArrayList<>();

    private List<TransferenciaDTO> transferenciasRecebidas = new ArrayList<>();

    public ContaDTO(){}

    public ContaDTO(Integer id, String numero, Double saldo, AgenciaDTO agencia, ClienteDTO cliente) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
        this.agencia = agencia;
        this.cliente = cliente;
    }

    public ContaDTO(Conta entity) {
        this.id = entity.getId();
        this.numero = entity.getNumero();
        this.saldo = entity.getSaldo();
        this.agencia = new AgenciaDTO(entity.getAgencia());
        this.cliente = new ClienteDTO(entity.getCliente());

        entity.getTransferenciasRealizadas().forEach(x -> this.transferenciasRealizadas.add(new TransferenciaDTO(x)));
        entity.getTransferenciasRecebidas().forEach(x -> this.transferenciasRecebidas.add(new TransferenciaDTO(x)));
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<TransferenciaDTO> getTransferenciasRealizadas() {
        return transferenciasRealizadas;
    }

    public List<TransferenciaDTO> getTransferenciasRecebidas() {
        return transferenciasRecebidas;
    }
}
