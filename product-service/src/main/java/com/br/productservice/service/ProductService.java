package com.br.productservice.service;

import com.br.productservice.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(UUID productId, ProductDTO productDTO);
    List<ProductDTO> listProducts();

    ProductDTO findProductById(UUID productId);

    void deleteProducts(UUID productId);

}
