package com.br.productservice.repository;

import com.br.productservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    UserDetails findByEmail(String email);
}