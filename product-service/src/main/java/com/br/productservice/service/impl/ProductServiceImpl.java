package com.br.productservice.service.impl;

import com.br.productservice.dto.ProductDTO;
import com.br.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDTO createProduct(ProductDTO product) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO product) {
        return null;
    }

    @Override
    public List<ProductDTO> listProducts() {
        return null;
    }

    @Override
    public void deleteProducts(UUID productId) {

    }
}
