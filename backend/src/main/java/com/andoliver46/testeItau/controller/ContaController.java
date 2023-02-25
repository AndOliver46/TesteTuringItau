package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.dtos.BuscarContaDTO;
import com.andoliver46.testeItau.dtos.ClienteMinDTO;
import com.andoliver46.testeItau.dtos.ContaDTO;
import com.andoliver46.testeItau.dtos.ContaMinDTO;
import com.andoliver46.testeItau.services.ContaService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cliente")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/informacoes")
    public ResponseEntity<ContaMinDTO> informacoes(){
        ContaMinDTO dto = contaService.informacoes();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/buscarConta")
    public ResponseEntity<BuscarContaDTO> buscarConta(@RequestBody BuscarContaDTO dto){
        BuscarContaDTO newDto = contaService.buscarConta(dto);
        return ResponseEntity.ok(newDto);
    }


}
