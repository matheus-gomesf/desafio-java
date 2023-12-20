package com.br.productservice.service;

import com.br.productservice.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDTO createProduct(ProductDTO product);
    ProductDTO updateProduct(ProductDTO product);
    List<ProductDTO> listProducts();
    void deleteProducts(UUID productId);

}
