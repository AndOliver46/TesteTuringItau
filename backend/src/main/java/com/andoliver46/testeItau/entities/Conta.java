package com.andoliver46.testeItau.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tb_conta")
public class Conta implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String numero;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private Double saldo = 0.00;

    @Column(nullable = false)
    private String authoritie;

    @ManyToOne
    @JoinColumn(name = "agencia_id")
    private Agencia agencia;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "emissor")
    private List<Transferencia> transferenciasRealizadas = new ArrayList<>();

    @OneToMany(mappedBy = "receptor")
    private List<Transferencia> transferenciasRecebidas = new ArrayList<>();

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

    public String getAuthoritie() {
        return authoritie;
    }

    public void setAuthoritie(String authoritie) {
        this.authoritie = authoritie;
    }

    public void depositar(Double valor){
        this.saldo += valor;
    }

    public void transferir(Double valor){
        this.saldo -= valor;
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
        Conta conta = (Conta) o;
        return id.equals(conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(this.authoritie)));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.numero;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", senha='" + senha + '\'' +
                ", saldo=" + saldo +
                ", authoritie='" + authoritie + '\'' +
                ", agencia=" + agencia +
                ", cliente=" + cliente +
                ", transferenciasRealizadas=" + transferenciasRealizadas +
                ", transferenciasRecebidas=" + transferenciasRecebidas +
                '}';
    }
}
