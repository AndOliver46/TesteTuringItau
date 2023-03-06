package com.andoliver46.testeItau.controller;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthenticationControllerTests {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private AuthenticationRequest authenticationRequestValido;
    private AuthenticationRequest authenticationRequestInvalido;

    private String usernameValid;
    private String password;

    @BeforeEach
    void setUp() throws Exception {
        authenticationRequestValido = Factory.authenticationRequestValido();
        authenticationRequestInvalido = Factory.authenticationRequestInvalido();

        usernameValid = "21554";
        password = "Senha12$";
    }

    @Test
    public void authenticateDeveRetornarStatusOkETokenQuandoCredenciaisValidas() throws Exception {

        String jsonBody = mapper.writeValueAsString(authenticationRequestValido);

        ResultActions result = mockMvc.perform(post("/api/login")
                .content(jsonBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.token").exists());
        result.andExpect(jsonPath("$.type").value("Bearer "));
    }

    @Test
    public void loginDeveRetornarBadRequestEMensagemQuandoCredenciaisInvalidas() throws Exception {

        String jsonBody = mapper.writeValueAsString(authenticationRequestInvalido);

        ResultActions result = mockMvc.perform(post("/api/login")
                .content(jsonBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.success").value("false"));
        result.andExpect(jsonPath("$.message").value("Credenciais invalidas"));
    }

    @Test
    public void logoutDeveRetornarCreatedEMensagemQuandoUsuarioLogado() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, usernameValid, password);

        ResultActions result = mockMvc.perform(delete("/api/logout")
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.success").value("true"));
        result.andExpect(jsonPath("$.message").value("User logout with successfully"));
    }

    @Test
    public void logoutDeveRetornarUnauthorizedQuandoUsuarioNaoLogado() throws Exception {

        ResultActions result = mockMvc.perform(delete("/api/logout")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }
}
