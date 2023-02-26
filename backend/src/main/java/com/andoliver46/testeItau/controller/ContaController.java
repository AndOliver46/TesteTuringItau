package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.dtos.*;
import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.services.ContaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cliente")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/informacoes")
    public ResponseEntity<ContaMinDTO> informacoes(){
        ContaMinDTO dto = contaService.informacoes();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/criarConta")
    public ResponseEntity<ContaDTO> criarConta(@RequestBody @Valid CriarContaDTO dto){
        ContaDTO newDto = contaService.criarConta(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }
}
