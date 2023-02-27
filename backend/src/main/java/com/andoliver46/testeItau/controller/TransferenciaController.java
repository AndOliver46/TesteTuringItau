package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.auth.ApiResponse;
import com.andoliver46.testeItau.dtos.*;
import com.andoliver46.testeItau.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping(value = "/transferir")
    public ResponseEntity<?> transferir(@RequestBody RealizarTransferenciaDTO dto){
        TransferenciaMinDTO newDto;
        try{
            newDto = transferenciaService.transferir(dto);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
        return ResponseEntity.ok(newDto);
    }


}
