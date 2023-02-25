package com.andoliver46.testeItau.auth;

import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.repositories.ContaRepository;
import com.andoliver46.testeItau.services.exceptions.ForbiddenException;
import com.andoliver46.testeItau.services.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public UserDetails loadUserByUsername(String numero) throws UsernameNotFoundException {
        Conta conta = contaRepository.findByNumero(numero).orElseThrow(() -> new UsernameNotFoundException("Conta não encontrada."));
        return conta;
    }

    @Transactional(readOnly = true)
    public Conta authenticated() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return contaRepository.findByNumero(username).orElseThrow(() -> new UnauthorizedException("Invalid user"));
    }

    public void validateSelf(Integer id) {
        Conta conta = authenticated();
        if(!conta.getId().equals(id)) {
            throw new ForbiddenException("Access denied");
        }
    }
}
