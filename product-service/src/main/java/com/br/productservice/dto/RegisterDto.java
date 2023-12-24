package com.br.productservice.dto;

import com.br.productservice.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(@NotNull @Email String email, @NotNull String password, @NotNull UserRole role ) {
    
}