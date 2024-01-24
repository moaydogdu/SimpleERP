package org.example.simpleerp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.simpleerp.common.model.CustomPage;
import org.example.simpleerp.common.model.dto.CustomPagingResponse;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.product.request.ProductCreateRequest;
import org.example.simpleerp.model.dto.product.request.ProductPagingRequest;
import org.example.simpleerp.model.dto.product.request.ProductUpdateRequest;
import org.example.simpleerp.model.mapper.product.ProductMapper;
import org.example.simpleerp.service.product.ProductCreateService;
import org.example.simpleerp.service.product.ProductDeleteService;
import org.example.simpleerp.service.product.ProductService;
import org.example.simpleerp.service.product.ProductUpdateService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;
    private final ProductCreateService productCreateService;
    private final ProductUpdateService productUpdateService;
    private final ProductDeleteService productDeleteService;

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody @Valid final ProductCreateRequest productCreateRequest
    ) {
        final Product createdProduct = productCreateService
                .createProduct(productCreateRequest);

        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(
            @PathVariable("productId") @UUID final String productId
    ) {
        final Product productFromDB = productService
                .getProductById(productId);

        return ResponseEntity.ok(productFromDB);
    }

    @GetMapping
    public ResponseEntity<CustomPagingResponse<Product>> getProducts(
            @RequestBody @Valid final ProductPagingRequest productPagingRequest
    ) {
        final CustomPage<Product> productsFromDB = productService
                .getProducts(productPagingRequest);

        final CustomPagingResponse<Product> response = ProductMapper
                .toCustomPagingResponse(productsFromDB);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @RequestBody @Valid final ProductUpdateRequest productUpdateRequest,
            @PathVariable("productId")@UUID final String productId
    ) {
        final Product updatedProduct = productUpdateService
                .updateProduct(productId,productUpdateRequest);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("productId")@UUID final String productId
    ) {
        productDeleteService.deleteProductById(productId);

        return ResponseEntity.ok().build();
    }

}
