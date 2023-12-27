package com.br.productservice.builder;

import com.br.productservice.dto.ProductDTO;
import com.br.productservice.entity.ProductEntity;
import com.br.productservice.enums.ProductCategory;

import java.math.BigInteger;
import java.util.UUID;

public class ProductBuilder {

    private static final UUID ID = UUID.randomUUID();
    public static ProductDTO getOneDTOBebida() {
        return ProductDTO.builder()
                .name("Coca")
                .price(BigInteger.TEN)
                .category(ProductCategory.BEBIDA)
                .build();
    }

    public static ProductEntity getOneEntityBebida() {
        return ProductEntity.builder()
                .id(ID)
                .name("Coca")
                .price(BigInteger.TEN)
                .category(ProductCategory.BEBIDA)
                .build();
    }

    public static ProductDTO getOneDTOSobremesa() {
        return ProductDTO.builder()
                .name("Pudim")
                .price(BigInteger.TEN)
                .category(ProductCategory.SOBREMESA)
                .build();
    }

    public static ProductEntity getOneEntitySobremesa() {
        return ProductEntity.builder()
                .id(ID)
                .name("Pudim")
                .price(BigInteger.TEN)
                .category(ProductCategory.SOBREMESA)
                .build();
    }

    public static ProductDTO getOneDTOIdsSobremesa() {
        return ProductDTO.builder()
                .id(ID)
                .name("Pudim")
                .price(BigInteger.TEN)
                .category(ProductCategory.SOBREMESA)
                .build();
    }

    public static ProductDTO getOneDTOBrigadeiro() {
        return ProductDTO.builder()
                .id(UUID.fromString("22c2638c-c59e-4b1b-a450-db4c8a7c6a2b"))
                .name("Brigadeiro")
                .price(BigInteger.valueOf(200))
                .category(ProductCategory.SOBREMESA)
                .build();
    }
}
