package org.example.simpleerp.service.product;

import org.example.simpleerp.base.BaseServiceTest;
import org.example.simpleerp.exception.product.ProductNotFoundException;
import org.example.simpleerp.exception.product.UnableToDeleteProductException;
import org.example.simpleerp.model.entity.OrderProductEntity;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.product.impl.ProductDeleteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductDeleteServiceImplTest extends BaseServiceTest {
    @InjectMocks
    private ProductDeleteServiceImpl productDeleteService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void givenValidProductEntity_whenDeleteProductById_thenDeleteProduct() {
        // Given
        final String mockValidProductId = UUID.randomUUID().toString();

        final ProductEntity mockValidProductEntityToBeDelete =
                ProductEntity.builder()
                        .id(mockValidProductId)
                        .orderProductEntities(List.of())
                        .build();

        // When
        Mockito.when(productRepository.findById(mockValidProductId))
                .thenReturn(Optional.of(mockValidProductEntityToBeDelete));

        // Then
        Assertions.assertDoesNotThrow(
                () -> productDeleteService.deleteProductById(mockValidProductId)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findById(mockValidProductId);

        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).delete(mockValidProductEntityToBeDelete);
    }

    @Test
    void givenInvalidProductEntity_whenDeleteProductById_thenThrowUnableToDeleteProductException()
    {
        // Given
        final String mockValidProductId = UUID.randomUUID().toString();

        final ProductEntity mockInvalidProductEntityToBeDelete =
                ProductEntity.builder()
                        .id(mockValidProductId)
                        .orderProductEntities(List.of(
                                OrderProductEntity.builder().id(UUID.randomUUID().toString()).build(),
                                OrderProductEntity.builder().id(UUID.randomUUID().toString()).build()
                        ))
                        .build();

        // When
        Mockito.when(productRepository.findById(mockValidProductId))
                .thenReturn(Optional.of(mockInvalidProductEntityToBeDelete));

        // Then
        Assertions.assertThrowsExactly(
                UnableToDeleteProductException.class,
                () -> productDeleteService.deleteProductById(mockValidProductId)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findById(mockValidProductId);

        Mockito.verify(
                productRepository,
                Mockito.times(0)
        ).delete(mockInvalidProductEntityToBeDelete);

    }

    @Test
    void givenNonExistProduct_whenDeleteProductById_thenThrowProductNotFoundException()
    {
        // Given
        final String mockValidProductId = UUID.randomUUID().toString();

        // When
        Mockito.when(productRepository.findById(mockValidProductId))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                ProductNotFoundException.class,
                () -> productDeleteService.deleteProductById(mockValidProductId)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findById(mockValidProductId);

        Mockito.verify(
                productRepository,
                Mockito.times(0)
        ).delete(Mockito.any(ProductEntity.class));
    }
}
