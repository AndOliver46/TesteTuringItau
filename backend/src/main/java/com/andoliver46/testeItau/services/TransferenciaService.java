package com.andoliver46.testeItau.services;

import com.andoliver46.testeItau.dtos.RealizarTransferenciaDTO;
import com.andoliver46.testeItau.dtos.TransferenciaMinDTO;
import com.andoliver46.testeItau.entities.Conta;
import com.andoliver46.testeItau.entities.Transferencia;
import com.andoliver46.testeItau.entities.exceptions.SameAccountException;
import com.andoliver46.testeItau.entities.exceptions.ValueLimitExcpetion;
import com.andoliver46.testeItau.enums.TipoTransferencia;
import com.andoliver46.testeItau.repositories.ContaRepository;
import com.andoliver46.testeItau.repositories.TransferenciaRepository;
import com.andoliver46.testeItau.services.exceptions.ForbiddenException;
import com.andoliver46.testeItau.services.exceptions.ValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;

@Service
public class TransferenciaService {

    @Autowired
    private ContaService contaService;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public TransferenciaMinDTO transferir(RealizarTransferenciaDTO dto) {
        Conta emissor = contaService.retornarMinhaConta();
        Conta receptor = contaService.buscarOutraConta(dto.getReceptor());

        Transferencia transferencia = new Transferencia();
        transferencia.setDataHora(Instant.now());
        transferencia.setTipo(TipoTransferencia.valueOf(dto.getTipo()));
        transferencia.setValor(dto.getValor());
        transferencia.setEmissor(emissor);
        transferencia.setReceptor(receptor);

        try{
            transferencia.realizarTransferencia();
        }catch(ValueLimitExcpetion e){
            throw new ValueException(e.getMessage());
        }catch (SameAccountException e){
            throw new ForbiddenException(e.getMessage());
        }
        transferenciaRepository.save(transferencia);

        emissor.getTransferenciasRealizadas().add(transferencia);
        receptor.getTransferenciasRecebidas().add(transferencia);
        contaRepository.saveAll(Arrays.asList(emissor, receptor));

        return new TransferenciaMinDTO(transferencia, emissor.getSaldo(), receptor.getSaldo());
    }
}
