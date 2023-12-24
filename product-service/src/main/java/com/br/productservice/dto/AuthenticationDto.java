package com.br.productservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDto(@NotNull @Email String email, @NotNull String password) {
    
}