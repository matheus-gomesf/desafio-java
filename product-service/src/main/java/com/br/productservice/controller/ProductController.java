package com.br.productservice.controller;

import com.br.productservice.dto.ProductDTO;
import com.br.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "Product controller"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Create product"
    )
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        ProductDTO product = productService.createProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update product"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody ProductDTO productDTO) {
        ProductDTO product = productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(
            summary = "List all product"
    )
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> list(Pageable pageable){
        Page<ProductDTO> listProducts = productService.listProducts(pageable);
        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @Operation(
            summary = "Get product by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable UUID id){
        ProductDTO product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete product by id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        productService.deleteProducts(id);
        return ResponseEntity.ok().build();
    }
}
