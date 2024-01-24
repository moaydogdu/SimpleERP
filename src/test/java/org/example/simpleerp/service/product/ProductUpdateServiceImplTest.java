package org.example.simpleerp.service.product;

import org.example.simpleerp.base.BaseServiceTest;
import org.example.simpleerp.builder.dto.product.ProductUpdateRequestBuilder;
import org.example.simpleerp.builder.entity.ProductEntityBuilder;
import org.example.simpleerp.exception.product.ProductAlreadyExistException;
import org.example.simpleerp.exception.product.ProductNotFoundException;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.product.request.ProductUpdateRequest;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.product.impl.ProductUpdateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

public class ProductUpdateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private ProductUpdateServiceImpl productUpdateService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void givenValidProductUpdateRequestAndValidProductEntity_whenUpdateProduct_thenUpdateProductEntityAndReturnProductDomainModel(){
        // Given
        final String mockValidProductId = UUID.randomUUID().toString();

        final ProductUpdateRequest mockValidProductUpdateRequest = new ProductUpdateRequestBuilder()
                .withValidField()
                .withNumber(2050L)
                .build();

        final ProductEntity mockValidProductEntityToBeUpdate = new ProductEntityBuilder()
                .withValidFields()
                .withId(mockValidProductId)
                .withNumber(2050L)
                .build();

        // When
        Mockito.when(productRepository.findById(mockValidProductId))
                .thenReturn(Optional.of(mockValidProductEntityToBeUpdate));

        // Then
        final Product updatedProductResponse = productUpdateService
                .updateProduct(mockValidProductId,mockValidProductUpdateRequest);

        Assertions.assertEquals(
                updatedProductResponse.getName(),
                mockValidProductUpdateRequest.getName()
        );

        Assertions.assertEquals(
                updatedProductResponse.getPrice(),
                mockValidProductUpdateRequest.getPrice()
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findById(mockValidProductId);

        Mockito.verify(
                productRepository,
                Mockito.times(0)
        ).existsProductEntityByNumber(Mockito.anyLong());

        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).save(mockValidProductEntityToBeUpdate);

    }

    @Test
    void givenNonExistProductEntity_whenUpdateProduct_thenThrowProductNotFoundException() {
        // Given
        final String mockValidProductId = UUID.randomUUID().toString();

        final ProductUpdateRequest mockValidProductUpdateRequest = new ProductUpdateRequestBuilder()
                .withValidField()
                .withNumber(2050L)
                .build();

        // When
        Mockito.when(productRepository.findById(mockValidProductId))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                ProductNotFoundException.class,
                () -> productUpdateService.updateProduct(mockValidProductId,mockValidProductUpdateRequest)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findById(mockValidProductId);

        Mockito.verify(
                productRepository,
                Mockito.times(0)
        ).existsProductEntityByNumber(Mockito.anyLong());

        Mockito.verify(
                productRepository,
                Mockito.times(0)
        ).save(Mockito.any(ProductEntity.class));
    }

    @Test
    void givenInvalidProductUpdateRequest_whenUpdateProduct_thenThrowProductAlreadyExistException() {
        // Given
        final String mockValidProductId = UUID.randomUUID().toString();

        final ProductUpdateRequest mockValidProductUpdateRequest = new ProductUpdateRequestBuilder()
                .withValidField()
                .withNumber(2050L)
                .build();

        final ProductEntity mockValidProductEntityToBeUpdate = new ProductEntityBuilder()
                .withValidFields()
                .withId(mockValidProductId)
                .withNumber(2051L)
                .build();

        // When
        Mockito.when(productRepository.findById(mockValidProductId))
                .thenReturn(Optional.of(mockValidProductEntityToBeUpdate));

        Mockito.when(productRepository
                        .existsProductEntityByNumber(mockValidProductUpdateRequest.getNumber())
                )
                .thenReturn(true);

        // Then
        Assertions.assertThrowsExactly(
                ProductAlreadyExistException.class,
                () -> productUpdateService.updateProduct(mockValidProductId,mockValidProductUpdateRequest)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findById(mockValidProductId);

        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).existsProductEntityByNumber(mockValidProductUpdateRequest.getNumber());

        Mockito.verify(
                productRepository,
                Mockito.times(0)
        ).save(Mockito.any(ProductEntity.class));
    }

}
