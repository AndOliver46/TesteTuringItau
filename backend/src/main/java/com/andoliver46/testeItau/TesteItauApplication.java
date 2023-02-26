package com.andoliver46.testeItau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TesteItauApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteItauApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("Senha12$"));
	}

}
