package com.andoliver46.testeItau.services;

import com.andoliver46.testeItau.dtos.BuscarContaDTO;
import com.andoliver46.testeItau.dtos.ClienteMinDTO;
import com.andoliver46.testeItau.dtos.ContaMinDTO;
import com.andoliver46.testeItau.entities.Cliente;
import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.repositories.ContaRepository;
import com.andoliver46.testeItau.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Transactional(readOnly = true)
    public ContaMinDTO informacoes(){
        String numero = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Conta> optional = contaRepository.findByNumero(numero);
        Conta obj = optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
        return new ContaMinDTO(obj);
    }

    @Transactional(readOnly = true)
    public Conta retornarMinhaConta(){
        String numero = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Conta> optional = contaRepository.findByNumero(numero);
        return optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
    }

    @Transactional(readOnly = true)
    public BuscarContaDTO buscarConta(BuscarContaDTO dto) {
        Optional<Conta> optional = contaRepository.findByNumero(dto.getConta());
        Conta conta = optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
        if(!dto.getAgencia().equals(conta.getAgencia().getCodigo())){
            throw new EntityNotFoundException("Cliente não localizado");
        }

        BuscarContaDTO newDto = new BuscarContaDTO();
        newDto.setConta(conta.getNumero());
        newDto.setAgencia(conta.getAgencia().getCodigo());
        newDto.setNome(conta.getCliente().getNome());
        newDto.setCpf(conta.getCliente().getCpf());

        return newDto;
    }

    @Transactional(readOnly = true)
    public Conta buscarConta(String numero) {
        Optional<Conta> optional = contaRepository.findByNumero(numero);
        Conta conta = optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
        return conta;
    }

    @Transactional(readOnly = true)
    public void verificarExistenciaDeConta(BuscarContaDTO dto) {
        Optional<Conta> optional = contaRepository.findByNumero(dto.getConta());
        Conta conta = optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
        if(!dto.getAgencia().equals(conta.getAgencia().getCodigo())){
            throw new EntityNotFoundException("Cliente não localizado");
        }
    }


}
