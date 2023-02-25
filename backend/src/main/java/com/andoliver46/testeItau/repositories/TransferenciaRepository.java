package com.andoliver46.testeItau.repositories;

import com.andoliver46.testeItau.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
}
