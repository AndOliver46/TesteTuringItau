package com.andoliver46.testeItau.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_access")
public class UserAccess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String token;

    public UserAccess(String token) {
        this.token = token;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
