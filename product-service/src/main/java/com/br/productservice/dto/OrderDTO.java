package com.br.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderDTO {
    private UUID id;

    private List<ProductDTO> products;

    private BigInteger totalPrice;
}