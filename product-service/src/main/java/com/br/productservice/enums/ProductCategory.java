package com.br.productservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductCategory {

    BEBIDA("BEBIDA", 1),
    ENTRADA("ENTRADA", 2),
    PRATO_PRINCIPAL("PRATO_PRINCIPAL", 3),
    SOBREMESA("SOBREMESA", 4);

    private final String category;
    private final Integer id;
}
