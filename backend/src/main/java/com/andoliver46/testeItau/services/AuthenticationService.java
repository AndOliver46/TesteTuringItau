package com.andoliver46.testeItau.services;

import com.andoliver46.testeItau.dtos.authentication.AuthenticationRequest;
import com.andoliver46.testeItau.dtos.authentication.AuthenticationResponse;
import com.andoliver46.testeItau.config.JwtService;
import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private ContaRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest userLoginDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));
        Conta conta = repository.findByNumero(userLoginDTO.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(conta);
        return new AuthenticationResponse(jwtToken);
    }
}
