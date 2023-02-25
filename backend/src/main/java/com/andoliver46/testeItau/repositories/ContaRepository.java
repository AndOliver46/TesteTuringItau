package com.andoliver46.testeItau.repositories;

import com.andoliver46.testeItau.entities.Cliente;
import com.andoliver46.testeItau.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    Optional<Conta> findByNumero(String numero);
}
