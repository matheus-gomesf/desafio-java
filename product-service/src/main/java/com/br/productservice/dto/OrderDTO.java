package com.br.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class OrderDTO {
    private UUID id;

    private List<ProductDTO> products;

    private BigInteger totalPrice;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}