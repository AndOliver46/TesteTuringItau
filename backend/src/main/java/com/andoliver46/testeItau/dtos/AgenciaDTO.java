package com.andoliver46.testeItau.dtos;

import com.andoliver46.testeItau.entities.Agencia;
import jakarta.persistence.Column;

import java.io.Serializable;

public class AgenciaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String codigo;

    public AgenciaDTO(){
    }

    public AgenciaDTO(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public AgenciaDTO(Agencia entity) {
        this.id = entity.getId();
        this.codigo = entity.getCodigo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
