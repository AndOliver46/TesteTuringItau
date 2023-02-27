package com.andoliver46.testeItau.auth;

import com.andoliver46.testeItau.entities.UserAccess;
import com.andoliver46.testeItau.repositories.UserAccessRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserAccessRepository userAccessRepository;

    @PostMapping(value = "/login", produces = { "application/json" })
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest request){

        AuthenticationResponse response = null;

        try {
            response = authenticationService.authenticate(request);
        } catch (DisabledException e) {
            return new ResponseEntity(new ApiResponse(false, "Usuario inativo"), HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            return new ResponseEntity(new ApiResponse(false, "Credenciais invalidas"), HttpStatus.BAD_REQUEST);
        }

        String jwt = response.getToken();
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @DeleteMapping(value = "/logout")
    public ResponseEntity<?> removeToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");
        URI location = null;

        if(token != null){
            UserAccess userAccess = new UserAccess(token.replace("Bearer ", ""));
            userAccessRepository.save(userAccess);
            location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/login")
                    .buildAndExpand(userAccess.getCodigo()).toUri();
        }

        return ResponseEntity.created(location).body(new ApiResponse(true, "User logout with successfully"));
    }

}
