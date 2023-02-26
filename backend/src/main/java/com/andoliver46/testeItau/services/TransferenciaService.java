package com.andoliver46.testeItau.services;

import com.andoliver46.testeItau.dtos.BuscarContaDTO;
import com.andoliver46.testeItau.dtos.RealizarTransferenciaDTO;
import com.andoliver46.testeItau.dtos.TransferenciaDTO;
import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.entities.Transferencia;
import com.andoliver46.testeItau.enums.TipoTransferencia;
import com.andoliver46.testeItau.repositories.ContaRepository;
import com.andoliver46.testeItau.repositories.TransferenciaRepository;
import com.andoliver46.testeItau.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    private ContaService contaService;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public TransferenciaDTO transferir(RealizarTransferenciaDTO dto) {
        Conta emissor = contaService.retornarMinhaConta();
        Conta receptor = contaService.buscarOutraConta(dto.getReceptor());

        Transferencia transferencia = new Transferencia();
        transferencia.setDataHora(Instant.now());
        transferencia.setTipo(TipoTransferencia.valueOf(dto.getTipo()));
        transferencia.setValor(dto.getValor());
        transferencia.setEmissor(emissor);
        transferencia.setReceptor(receptor);

        transferencia.realizarTransferencia();
        transferenciaRepository.save(transferencia);

        emissor.getTransferenciasRealizadas().add(transferencia);
        receptor.getTransferenciasRecebidas().add(transferencia);
        contaRepository.saveAll(Arrays.asList(emissor, receptor));

        return new TransferenciaDTO();
    }
}
