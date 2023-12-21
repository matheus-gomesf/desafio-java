package com.br.productservice.builder;

import com.br.productservice.dto.OrderDTO;
import com.br.productservice.entity.OrderEntity;

import java.math.BigInteger;
import java.util.Collections;
import java.util.UUID;

public class OrderBuilder {

    private static final UUID ID = UUID.randomUUID();

    public static OrderDTO getOne() {
        return OrderDTO.builder()
                .products(Collections.singletonList(ProductBuilder.getOneDTOBebida()))
                .totalPrice(BigInteger.TEN)
                .build();
    }

    public static OrderEntity getOneEntity() {
        return OrderEntity.builder()
                .id(ID)
                .products(Collections.singletonList(ProductBuilder.getOneEntityBebida()))
                .totalPrice(BigInteger.TEN)
                .build();
    }
}
