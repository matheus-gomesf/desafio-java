package com.br.productservice.dto;

import com.br.productservice.enums.ProductCategory;
import lombok.*;

import java.math.BigInteger;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class ProductDTO {
    private UUID id;

    private String name;

    private BigInteger price;

    private ProductCategory category;
}