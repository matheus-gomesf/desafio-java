package com.br.productservice.controller;

import com.br.productservice.dto.AuthetinticationDto;
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
    public ResponseEntity<Object> login(@RequestBody @Valid AuthetinticationDto authetinticationDto){
        return authorizationService.login(authetinticationDto);
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }
}