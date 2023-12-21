package com.br.productservice.dto;

import lombok.*;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {
    private UUID id;

    private List<ProductDTO> products;

    private BigInteger totalPrice;
}