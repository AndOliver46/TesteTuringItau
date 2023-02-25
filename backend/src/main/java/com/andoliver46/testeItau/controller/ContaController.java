package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.dtos.ClienteMinDTO;
import com.andoliver46.testeItau.dtos.ContaMinDTO;
import com.andoliver46.testeItau.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cliente")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/informacoes")
    public ResponseEntity<ContaMinDTO> informacoes(){
        ContaMinDTO dto = contaService.informacoes();
        return ResponseEntity.ok(dto);
    }

}
