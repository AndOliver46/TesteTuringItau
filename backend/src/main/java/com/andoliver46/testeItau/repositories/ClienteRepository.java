package com.andoliver46.testeItau.repositories;

import com.andoliver46.testeItau.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
