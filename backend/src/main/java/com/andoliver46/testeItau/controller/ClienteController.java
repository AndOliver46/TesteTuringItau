package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.dtos.ClienteDTO;
import com.andoliver46.testeItau.dtos.ClienteMinDTO;
import com.andoliver46.testeItau.entities.Cliente;
import com.andoliver46.testeItau.services.ClienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteMinDTO> findById(@PathVariable Integer id){
        ClienteMinDTO dto = clienteService.findById(id);
        return ResponseEntity.ok(dto);
    }


}
