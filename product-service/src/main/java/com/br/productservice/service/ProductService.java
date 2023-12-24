package com.br.productservice.service;

import com.br.productservice.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(UUID productId, ProductDTO productDTO);
    Page<ProductDTO> listProducts(Pageable pageable);
    List<ProductDTO> findAllByIds(List<UUID> productsIds);

    ProductDTO findProductById(UUID productId);

    void deleteProducts(UUID productId);

}
