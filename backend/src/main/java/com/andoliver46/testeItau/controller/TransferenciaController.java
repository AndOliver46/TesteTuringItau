package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.dtos.TransferenciaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transferencia")
public class TransferenciaController {

    @PostMapping("/transferir")
    public ResponseEntity<TransferenciaDTO> transferir(@RequestBody)

}
