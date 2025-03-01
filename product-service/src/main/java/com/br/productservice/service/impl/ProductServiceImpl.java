package com.br.productservice.service.impl;

import com.br.productservice.dto.ProductDTO;
import com.br.productservice.entity.ProductEntity;
import com.br.productservice.exception.ParameterNotValidException;
import com.br.productservice.exception.ResourceNotFoundException;
import com.br.productservice.repository.ProductRepository;
import com.br.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.br.productservice.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        val toSave = PRODUCT_MAPPER.dtoToEntity(productDTO);

        val saved = productRepository.save(toSave);

        return PRODUCT_MAPPER.entityToDto(saved);
    }

    @Override
    public ProductDTO updateProduct(UUID productId, ProductDTO productDTO) {

        validateProductId(productId);

        ProductEntity productEntity = getProduct(productId);

        PRODUCT_MAPPER.updateFromDto(productDTO, productEntity);
        ProductEntity saved = productRepository.save(productEntity);

        return PRODUCT_MAPPER.entityToDto(saved);
    }

    @Override
    public Page<ProductDTO> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(PRODUCT_MAPPER::entityToDto);
    }

    @Override
    public List<ProductDTO> findAllByIds(List<UUID> productsIds) {

        List<ProductEntity> productEntities = productsIds.stream().map(this::getProduct).toList();

        return productEntities.stream().map(PRODUCT_MAPPER::entityToDto).toList();
    }

    @Override
    public ProductDTO findProductById(UUID productId) {

        validateProductId(productId);

        ProductEntity productEntity = getProduct(productId);

        return PRODUCT_MAPPER.entityToDto(productEntity);
    }

    @Override
    public void deleteProducts(UUID productId) {

        validateProductId(productId);

        getProduct(productId);

        productRepository.deleteById(productId);
    }

    private ProductEntity getProduct(UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId.toString()));
    }


    private void validateProductId(UUID productId) {
        if (Objects.isNull(productId)) {
            throw new ParameterNotValidException("ProductId", "null");
        }
    }
}
