package com.andoliver46.testeItau.services;

import com.andoliver46.testeItau.dtos.ClienteDTO;
import com.andoliver46.testeItau.dtos.ClienteMinDTO;
import com.andoliver46.testeItau.entities.Cliente;
import com.andoliver46.testeItau.repositories.ClienteRepository;
import com.andoliver46.testeItau.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public ClienteMinDTO findById(Integer id){
        Optional<Cliente> optional = clienteRepository.findById(id);
        Cliente obj = optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
        return new ClienteMinDTO(obj);
    }
}
