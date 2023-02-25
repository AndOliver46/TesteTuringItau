package com.andoliver46.testeItau.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String numero;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "agencia_id")
    private Agencia agencia;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Conta(){
    }

    public Conta(Integer id, String numero, String senha, Agencia agencia) {
        this.id = id;
        this.numero = numero;
        this.senha = senha;
        this.saldo = 0.0;
        this.agencia = agencia;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void depositar(Double valor){
        this.saldo += valor;
    }

    public void sacar(Double valor){
        if(this.saldo >= valor ){
            this.saldo -= valor;
        }
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return id.equals(conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
