package com.andoliver46.testeItau.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas = new ArrayList<>();

    @OneToMany(mappedBy = "emissor")
    private List<Transferencia> transferenciasRealizadas = new ArrayList<>();

    @OneToMany(mappedBy = "receptor")
    private List<Transferencia> transferenciasRecebidas = new ArrayList<>();

    public Cliente(){
    }

    public Cliente(Integer id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
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

    public List<Conta> getContas() {
        return contas;
    }

    public List<Transferencia> getTransferenciasRealizadas() {
        return transferenciasRealizadas;
    }

    public List<Transferencia> getTransferenciasRecebidas() {
        return transferenciasRecebidas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
