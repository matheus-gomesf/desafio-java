package com.br.productservice.controller;

import com.br.productservice.dto.AuthenticationDto;
import com.br.productservice.dto.RegisterDto;
import com.br.productservice.service.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
   

    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto){
        return authorizationService.login(authenticationDto);
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody @Valid RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }
}