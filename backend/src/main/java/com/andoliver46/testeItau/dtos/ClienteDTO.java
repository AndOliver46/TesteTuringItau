package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Cliente;
import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.entities.Transferencia;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String cpf;

    private List<ContaDTO> contas = new ArrayList<>();

    public ClienteDTO(){
    }

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        cpf = entity.getCpf();

        entity.getContas().forEach(x -> this.contas.add(new ContaDTO(x)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
