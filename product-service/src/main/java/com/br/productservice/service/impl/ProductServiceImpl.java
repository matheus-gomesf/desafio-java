package com.br.productservice.service.impl;

import com.br.productservice.dto.ProductDTO;
import com.br.productservice.entity.ProductEntity;
import com.br.productservice.exception.ResourceNotFoundException;
import com.br.productservice.repository.ProductRepository;
import com.br.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.br.productservice.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        val toSave = PRODUCT_MAPPER.dtoToEntity(product);

        val saved = productRepository.save(toSave);

        return PRODUCT_MAPPER.entityToDto(saved);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {

        ProductEntity productEntity = getProduct(productDTO.getId());

        PRODUCT_MAPPER.updateFromDto(productDTO, productEntity);
        ProductEntity saved = productRepository.save(productEntity);

        return PRODUCT_MAPPER.entityToDto(saved);
    }

    @Override
    public List<ProductDTO> listProducts() {
        return productRepository.findAll().stream().map(PRODUCT_MAPPER::entityToDto).toList();
    }

    @Override
    public ProductDTO getProductById(UUID productId) {
        ProductEntity productEntity = getProduct(productId);

        return PRODUCT_MAPPER.entityToDto(productEntity);
    }

    @Override
    public void deleteProducts(UUID productId) {
        getProduct(productId);

        productRepository.deleteById(productId);
    }

    private ProductEntity getProduct(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId.toString()));
    }
}
