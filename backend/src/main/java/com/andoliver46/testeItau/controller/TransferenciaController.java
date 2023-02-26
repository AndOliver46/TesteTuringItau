package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.dtos.BuscarContaDTO;
import com.andoliver46.testeItau.dtos.RealizarTransferenciaDTO;
import com.andoliver46.testeItau.dtos.TransferenciaDTO;
import com.andoliver46.testeItau.dtos.TransferenciaMinDTO;
import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/transferir")
    public ResponseEntity<TransferenciaMinDTO> transferir(@RequestBody RealizarTransferenciaDTO dto){
        TransferenciaMinDTO newDto = transferenciaService.transferir(dto);
        return ResponseEntity.ok(newDto);
    }


}
