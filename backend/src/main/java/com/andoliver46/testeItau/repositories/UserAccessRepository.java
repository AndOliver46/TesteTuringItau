package com.andoliver46.testeItau.repositories;

import com.andoliver46.testeItau.entities.Agencia;
import com.andoliver46.testeItau.entities.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessRepository extends JpaRepository<UserAccess, Integer> {
    boolean existsByToken(String authToken);
}
