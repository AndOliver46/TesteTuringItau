package com.andoliver46.testeItau.dtos.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AuthenticationRequest {

    @NotBlank(message = "O campo número da conta deve ser preenchido!")
    private String username;
    @NotBlank(message = "O campo senha deve ser preenchido!")
    private String password;

    public AuthenticationRequest(){
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
