package com.andoliver46.testeItau.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andoliver46.testeItau.dtos.CriarContaDTO;
import com.andoliver46.testeItau.dtos.authentication.AuthenticationRequest;
import com.andoliver46.testeItau.tools.Factory;
import com.andoliver46.testeItau.tools.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ContaControllerTests {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private CriarContaDTO criarContaDTOValida;
    private CriarContaDTO criarContaDTOInvalida;

    private String usernameInvalid;
    private String usernameValid;
    private String password;

    @BeforeEach
    void setUp() throws Exception {
        criarContaDTOValida = Factory.criarContaValida();
        criarContaDTOInvalida = Factory.criarContaInvalida();

        usernameValid = "21554";
        usernameInvalid = "00000";
        password = "Senha12$";
    }

    @Test
    public void criarContaDeveRetornarStatusCreatedEDadosQuandoNumeroDeContaValido() throws Exception {

        String jsonBody = mapper.writeValueAsString(criarContaDTOValida);

        ResultActions result = mockMvc.perform(post("/api/cliente/criarConta").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.numero").value(criarContaDTOValida.getNumeroConta()));
        result.andExpect(jsonPath("$.saldo").value("0.0"));
        result.andExpect(jsonPath("$.agencia.codigo").value("8569"));
    }

    @Test
    public void criarContaDeveRetornarStatusBadRequestQuandoNumeroDeContaInvalido() throws Exception {

        String jsonBody = mapper.writeValueAsString(criarContaDTOInvalida);

        ResultActions result = mockMvc.perform(post("/api/cliente/criarConta").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.success").value("false"));
        result.andExpect(jsonPath("$.message").value("Já existe uma conta cadastrada com esse número!"));
    }

    @Test
    public void informacoesDeveRetornarInformacoesDaContaQuandoLogado() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, usernameValid, password);

        ResultActions result = mockMvc.perform(get("/api/cliente/informacoes")
                .header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.numero").exists());
        result.andExpect(jsonPath("$.saldo").exists());
        result.andExpect(jsonPath("$.cliente").exists());
        result.andExpect(jsonPath("$.agencia").exists());
    }

    @Test
    public void informacoesDeveRetornarUnauthorizedQuandoNaoLogado() throws Exception {

        ResultActions result = mockMvc.perform(get("/api/cliente/informacoes")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }

}
