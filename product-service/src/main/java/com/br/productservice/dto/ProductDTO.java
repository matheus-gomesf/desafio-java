package com.br.productservice.dto;

import com.br.productservice.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private UUID id;

    private String name;

    private BigInteger price;

    private ProductCategory category;
}
