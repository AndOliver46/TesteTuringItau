package com.andoliver46.testeItau.entities;

import com.andoliver46.testeItau.enums.TipoTransferencia;
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransferencia tipo;

    @ManyToOne
    @JoinColumn(name = "id_emissor")
    private Cliente emissor;

    @ManyToOne
    @JoinColumn(name = "id_receptor")
    private Cliente receptor;

    public Transferencia() {
    }

    public Transferencia(Integer id, Instant dataHora, Double valor, TipoTransferencia tipo, Cliente emissor, Cliente receptor) {
        this.id = id;
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
        this.emissor = emissor;
        this.receptor = receptor;
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

    public Cliente getEmissor() {
        return emissor;
    }

    public void setEmissor(Cliente emissor) {
        this.emissor = emissor;
    }

    public Cliente getReceptor() {
        return receptor;
    }

    public void setReceptor(Cliente receptor) {
        this.receptor = receptor;
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
