package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteMinDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String cpf;

    public ClienteMinDTO(){
    }

    public ClienteMinDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        cpf = entity.getCpf();
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
