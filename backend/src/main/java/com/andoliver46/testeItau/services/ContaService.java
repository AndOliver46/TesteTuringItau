package com.andoliver46.testeItau.services;

import com.andoliver46.testeItau.dtos.*;
import com.andoliver46.testeItau.entities.Cliente;
import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.repositories.AgenciaRepository;
import com.andoliver46.testeItau.repositories.ClienteRepository;
import com.andoliver46.testeItau.repositories.ContaRepository;
import com.andoliver46.testeItau.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaService implements UserDetailsService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private AgenciaRepository agenciaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public ContaMinDTO informacoes(){
        String numero = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Conta> optional = contaRepository.findByNumero(numero);
        Conta obj = optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
        return new ContaMinDTO(obj);
    }

    //Funções utilitárias
    @Transactional(readOnly = true)
    public Conta retornarMinhaConta(){
        String numero = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Conta> optional = contaRepository.findByNumero(numero);
        return optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
    }

    @Transactional(readOnly = true)
    public Conta buscarOutraConta(String numero) {
        Optional<Conta> optional = contaRepository.findByNumero(numero);
        Conta conta = optional.orElseThrow(() -> new EntityNotFoundException("Cliente não localizado"));
        return conta;
    }

    @Override
    public UserDetails loadUserByUsername(String numero) throws UsernameNotFoundException {
        return contaRepository.findByNumero(numero).orElseThrow(() -> new UsernameNotFoundException("COnta não localizada."));
    }

    public ContaDTO criarConta(CriarContaDTO dto) {

        Conta conta = new Conta();
        conta.setNumero(dto.getNumeroConta());
        conta.setSenha(new BCryptPasswordEncoder().encode(dto.getSenha()));
        conta.setAgencia(agenciaRepository.findByCodigo(dto.getCodigoAgencia()));
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getCpf());
        conta.setCliente(cliente);
        conta.setAuthoritie("ROLE_CLIENT");

        clienteRepository.save(cliente);
        contaRepository.save(conta);

        return new ContaDTO(conta);
    }
}
