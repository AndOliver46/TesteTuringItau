package com.andoliver46.testeItau.controller;

import com.andoliver46.testeItau.dtos.RealizarTransferenciaDTO;
import com.andoliver46.testeItau.tools.Factory;
import com.andoliver46.testeItau.tools.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TransferenciaControllerTests {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private RealizarTransferenciaDTO realizarTransferenciaTedValida;
    private RealizarTransferenciaDTO realizarTransferenciaPixInvalida;
    private RealizarTransferenciaDTO realizarTransferenciaContaInvalida;

    private String usernameValid;
    private String password;

    @BeforeEach
    void setUp() throws Exception {
        realizarTransferenciaTedValida = Factory.realizarTransferenciaTedValida();
        realizarTransferenciaPixInvalida = Factory.realizarTransferenciaPixInvalida();
        realizarTransferenciaContaInvalida = Factory.realizarTransferenciaContaInvalida();

        usernameValid = "21554";
        password = "Senha12$";
    }


    @Test
    public void transferirDeveRetornarUnauthorizedQuandoUsuarioNaoLogado() throws Exception {

        String jsonBody = mapper.writeValueAsString(realizarTransferenciaTedValida);

        ResultActions result = mockMvc.perform(post("/api/transferencias/transferir")
                .content(jsonBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void transferirDeveRetornarStatusOkQuandoUsuarioLogadoEDadosCorretos() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, usernameValid, password);
        String jsonBody = mapper.writeValueAsString(realizarTransferenciaTedValida);

        ResultActions result = mockMvc.perform(post("/api/transferencias/transferir")
                        .header("Authorization", "Bearer " + accessToken)
                .content(jsonBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.dataHora").exists());
        result.andExpect(jsonPath("$.valor").value("6000.0"));
        result.andExpect(jsonPath("$.tipo").value("TED"));
        result.andExpect(jsonPath("$.saldoEmissor").value("-6000.0"));
        result.andExpect(jsonPath("$.saldoReceptor").value("6000.0"));
    }

    @Test
    public void transferirDeveRetornarBadRequestEMensagemQuandoUsuarioLogadoEForaDeLimite() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, usernameValid, password);
        String jsonBody = mapper.writeValueAsString(realizarTransferenciaPixInvalida);

        ResultActions result = mockMvc.perform(post("/api/transferencias/transferir")
                .header("Authorization", "Bearer " + accessToken)
                .content(jsonBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.success").value("false"));
        result.andExpect(jsonPath("$.message").value("Limite PIX excedido, limite: R$5.000"));
    }

    @Test
    public void transferirDeveRetornarBadRequestEMensagemQuandoUsuarioLogadoEContaReceptorIncorreta() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, usernameValid, password);
        String jsonBody = mapper.writeValueAsString(realizarTransferenciaContaInvalida);

        ResultActions result = mockMvc.perform(post("/api/transferencias/transferir")
                .header("Authorization", "Bearer " + accessToken)
                .content(jsonBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.success").value("false"));
        result.andExpect(jsonPath("$.message").value("Cliente não localizado"));
    }
}
