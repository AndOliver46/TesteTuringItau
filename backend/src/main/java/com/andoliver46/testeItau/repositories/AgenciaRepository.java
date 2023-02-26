package com.andoliver46.testeItau.repositories;

import com.andoliver46.testeItau.entities.Agencia;
import com.andoliver46.testeItau.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {
    Agencia findByCodigo(String codigoAgencia);
}
