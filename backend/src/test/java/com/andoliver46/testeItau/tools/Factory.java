package com.andoliver46.testeItau.tools;

import com.andoliver46.testeItau.dtos.CriarContaDTO;
import com.andoliver46.testeItau.dtos.RealizarTransferenciaDTO;
import com.andoliver46.testeItau.dtos.authentication.AuthenticationRequest;

public class Factory {

	public static CriarContaDTO criarContaValida() {
		CriarContaDTO criarContaDTO = new CriarContaDTO("8569", "12345", "Senha12$", "Maria Aurelina","76765654890");
		return criarContaDTO;
	}

	public static CriarContaDTO criarContaInvalida() {
		CriarContaDTO criarContaDTO = new CriarContaDTO("8569", "21554", "Senha12$", "Maria Aurelina","76765654890");
		return criarContaDTO;
	}

	public static AuthenticationRequest authenticationRequestValido() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("21554", "Senha12$");
		return authenticationRequest;
	}

	public static AuthenticationRequest authenticationRequestInvalido() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("00000", "00000");
		return authenticationRequest;
	}

	public static RealizarTransferenciaDTO realizarTransferenciaTedValida() {
		RealizarTransferenciaDTO realizarTransferenciaDTO = new RealizarTransferenciaDTO("5000.00", "TED", "21675");
		return realizarTransferenciaDTO;
	}

	public static RealizarTransferenciaDTO realizarTransferenciaPixInvalida() {
		RealizarTransferenciaDTO realizarTransferenciaDTO = new RealizarTransferenciaDTO("6000.00", "PIX", "21675");
		return realizarTransferenciaDTO;
	}

	public static RealizarTransferenciaDTO realizarTransferenciaContaInvalida() {
		RealizarTransferenciaDTO realizarTransferenciaDTO = new RealizarTransferenciaDTO("5000.00", "PIX", "99999");
		return realizarTransferenciaDTO;
	}

}
