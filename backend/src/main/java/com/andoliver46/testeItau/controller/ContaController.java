package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.dtos.*;
import com.andoliver46.testeItau.dtos.authentication.ApiResponse;
import com.andoliver46.testeItau.services.ContaService;
import com.andoliver46.testeItau.services.exceptions.AccountAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/cliente")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping(value = "/informacoes")
    public ResponseEntity<ContaMinDTO> informacoes(){
        ContaMinDTO dto = contaService.informacoes();
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/criarConta")
    public ResponseEntity<?> criarConta(@RequestBody @Valid CriarContaDTO dto){
        try{
            ContaDTO newDto = contaService.criarConta(dto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{numero}").buildAndExpand(newDto.getNumero()).toUri();
            return ResponseEntity.created(uri).body(newDto);
        }catch (AccountAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }

    }
}
