package com.andoliver46.testeItau.entities;

import com.andoliver46.testeItau.entities.exceptions.SameAccountException;
import com.andoliver46.testeItau.entities.exceptions.ValueLimitExcpetion;
import com.andoliver46.testeItau.enums.TipoTransferencia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE" , nullable = false)
    private Instant dataHora;
    @Column(nullable = false)
    private Double valor;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TipoTransferencia tipo;

    @ManyToOne
    @JoinColumn(name = "id_emissor")
    private Conta emissor;

    @ManyToOne
    @JoinColumn(name = "id_receptor")
    private Conta receptor;

    public Transferencia() {
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

    public Conta getEmissor() {
        return emissor;
    }

    public void setEmissor(Conta emissor) {
        this.emissor = emissor;
    }

    public Conta getReceptor() {
        return receptor;
    }

    public void setReceptor(Conta receptor) {
        this.receptor = receptor;
    }

    public void realizarTransferencia(){
        verificarValorETipo();
        if(emissor.getNumero().equals(receptor.getNumero())){
            throw new SameAccountException("Transferencia para a mesma conta não permitida");
        }
        emissor.transferir(valor);
        receptor.depositar(valor);
    }

    private void verificarValorETipo() {
        if(valor > 5000.00 && tipo.equals(TipoTransferencia.PIX)){
            throw new ValueLimitExcpetion("Limite PIX excedido");
        }else if((valor < 5000.00 || valor > 10000.00) &&  tipo.equals(TipoTransferencia.TED)){
            throw new ValueLimitExcpetion("Limite TED excedido");
        }else if((valor < 10000.00) &&  tipo.equals(TipoTransferencia.DOC)){
            throw new ValueLimitExcpetion("Limite DOC excedido");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transferencia that = (Transferencia) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
