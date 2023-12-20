package com.br.productservice.service.impl;

import com.br.productservice.builder.ProductBuilder;
import com.br.productservice.dto.ProductDTO;
import com.br.productservice.entity.ProductEntity;
import com.br.productservice.exception.ParameterNotValidException;
import com.br.productservice.exception.ResourceNotFoundException;
import com.br.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductDTOServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private ProductDTO productBebida;
    private ProductEntity productEntityBebida;

    @BeforeEach
    void setUp() {
        productBebida = ProductBuilder.getOneDTOBebida();
        productEntityBebida = ProductBuilder.getOneEntityBebida();
    }

    @Test
    void successfullyCreateProduct() {

        when(productRepository.save(any())).thenReturn(productEntityBebida);

        ProductDTO productCreated = productService.createProduct(productBebida);

        assertNotNull(productCreated);
        assertEquals(productEntityBebida.getId(), productCreated.getId());
        assertEquals(productEntityBebida.getName(), productCreated.getName());
        assertEquals(productEntityBebida.getPrice(), productCreated.getPrice());
        assertEquals(productEntityBebida.getCategory(), productCreated.getCategory());
    }

    @Test
    void successfullyUpdateProduct() {

        ProductEntity productExpected = ProductBuilder.getOneEntitySobremesa();

        when(productRepository.findById(productEntityBebida.getId())).thenReturn(Optional.of(productEntityBebida));
        when(productRepository.save(any())).thenReturn(productExpected);

        ProductDTO productCreated = productService.updateProduct(productEntityBebida.getId(), ProductBuilder.getOneDTOSobremesa());

        assertNotNull(productCreated);
        assertEquals(productEntityBebida.getId(), productCreated.getId());
        assertEquals(productExpected.getName(), productCreated.getName());
        assertEquals(productExpected.getPrice(), productCreated.getPrice());
        assertEquals(productExpected.getCategory(), productCreated.getCategory());
    }

    @Test
    void errorUpdateProductWithNullId() {

        final ParameterNotValidException e = assertThrows(ParameterNotValidException.class, () -> productService.updateProduct(null, ProductBuilder.getOneDTOSobremesa()));

        assertNotNull(e);
        assertEquals(e.getMessage(), "ProductId can not be 'null'");
    }

    @Test
    void errorUpdateProductNotFound() {

        UUID productId = UUID.randomUUID();

        final ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(productId, ProductBuilder.getOneDTOSobremesa()));

        assertNotNull(e);
        assertEquals(e.getMessage(), format("Product not found with id : '%s'", productId));
    }

    @Test
    void successfullyListProduct() {

        when(productRepository.findAll()).thenReturn(Collections.singletonList(productEntityBebida));

        List<ProductDTO> productCreated = productService.listProducts();

        assertNotNull(productCreated);
        assertNotNull(productCreated.stream().findFirst());
        assertTrue(productCreated.stream().findFirst().isPresent());
        assertNotNull(productCreated.stream().findFirst().get());
        assertEquals(productEntityBebida.getId(), productCreated.stream().findFirst().get().getId());
    }

    @Test
    void successfullyFindProductById() {

        when(productRepository.findById(productEntityBebida.getId())).thenReturn(Optional.of(productEntityBebida));

        ProductDTO productDTO = productService.findProductById(productEntityBebida.getId());

        assertNotNull(productDTO);
        assertEquals(productEntityBebida.getId(), productDTO.getId());
        assertEquals(productEntityBebida.getName(), productDTO.getName());
        assertEquals(productEntityBebida.getPrice(), productDTO.getPrice());
        assertEquals(productEntityBebida.getCategory(), productDTO.getCategory());
    }

    @Test
    void errorFindProductByIdWithNullId() {

        final ParameterNotValidException e = assertThrows(ParameterNotValidException.class, () -> productService.findProductById(null));

        assertNotNull(e);
        assertEquals(e.getMessage(), "ProductId can not be 'null'");
    }

    @Test
    void errorFindProductByIdNotFound() {

        UUID productId = UUID.randomUUID();

        final ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> productService.findProductById(productId));

        assertNotNull(e);
        assertEquals(e.getMessage(), format("Product not found with id : '%s'", productId));
    }
}