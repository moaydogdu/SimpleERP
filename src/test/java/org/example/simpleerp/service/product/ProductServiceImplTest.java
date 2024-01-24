package org.example.simpleerp.service.product;

import org.example.simpleerp.base.BaseServiceTest;
import org.example.simpleerp.builder.dto.product.ProductPagingRequestBuilder;
import org.example.simpleerp.builder.entity.ProductEntityBuilder;
import org.example.simpleerp.common.model.CustomPage;
import org.example.simpleerp.exception.product.ProductNotFoundException;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.product.request.ProductPagingRequest;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.product.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductServiceImplTest extends BaseServiceTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void givenValidProductId_whenGetProductById_thenReturnProductDomainModel() {
        // Given
        final String mockValidProductId = UUID.randomUUID().toString();

        final ProductEntity mockValidProductEntity = new ProductEntityBuilder()
                .withValidFields()
                .withId(mockValidProductId)
                .build();

        // When
        Mockito.when(productRepository.findById(mockValidProductId))
                .thenReturn(Optional.of(mockValidProductEntity));

        // Then
        final Product productDomainModelResponse = productService
                .getProductById(mockValidProductId);

        Assertions.assertEquals(
                productDomainModelResponse.getName(),
                mockValidProductEntity.getName()
        );

        Assertions.assertEquals(
                productDomainModelResponse.getNumber(),
                mockValidProductEntity.getNumber()
        );

        Assertions.assertEquals(
                productDomainModelResponse.getPrice(),
                mockValidProductEntity.getPrice()
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findById(mockValidProductId);
    }

    @Test
    void givenNonExistProduct_whenGetProductById_thenThrowProductNotFoundException() {
        // Given
        final String mockValidProductId = UUID.randomUUID().toString();

        // When
        Mockito.when(productRepository.findById(mockValidProductId))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                ProductNotFoundException.class,
                () -> productService.getProductById(mockValidProductId)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findById(mockValidProductId);
    }

    @Test
    void givenValidPageOfProductEntities_whenGetProducts_thenReturnProductDomainModelsInCustomPage() {
        // Given
        final ProductPagingRequest mockValidProductPagingRequest = new ProductPagingRequestBuilder()
                .withValidFields()
                .build();

        final Page<ProductEntity> mockProductEntityPage = new PageImpl<>(
                List.of(
                        new ProductEntityBuilder().withValidFields().build(),
                        new ProductEntityBuilder().withValidFields().build()
                ),
                mockValidProductPagingRequest.toPageable(),
                2
        );

        // When
        Mockito.when(productRepository.findAll(mockValidProductPagingRequest.toPageable()))
                .thenReturn(mockProductEntityPage);

        // Then
        final CustomPage<Product> customProductPageResponse = productService
                .getProducts(mockValidProductPagingRequest);

        Assertions.assertEquals(
                customProductPageResponse.getContent().size(),
                2
        );

        Assertions.assertEquals(
                customProductPageResponse.getPageSize(),
                mockValidProductPagingRequest.toPageable().getPageSize()
        );

        Assertions.assertEquals(
                customProductPageResponse.getPageNumber(),
                mockValidProductPagingRequest.getPagination().getPageNumber()+1
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findAll(mockValidProductPagingRequest.toPageable());
    }

    @Test
    void givenEmptyPageOfProductEntities_whenGetProducts_thenThrowsProductNotFoundException() {
        // Given
        final ProductPagingRequest mockValidProductPagingRequest = new ProductPagingRequestBuilder()
                .withValidFields()
                .build();

        final Page<ProductEntity> mockProductEntityPage = new PageImpl<>(
                List.of(),
                mockValidProductPagingRequest.toPageable(),
                0
        );

        // When
        Mockito.when(productRepository.findAll(mockValidProductPagingRequest.toPageable()))
                .thenReturn(mockProductEntityPage);

        // Then
        Assertions.assertThrowsExactly(
                ProductNotFoundException.class,
                () -> productService.getProducts(mockValidProductPagingRequest)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findAll(mockValidProductPagingRequest.toPageable());
    }

    @Test
    void givenValidProductEntity_whenGetProductByNumber_thenReturnProductDomainModel() {
        // Given
        final ProductEntity mockValidProductEntity = new ProductEntityBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(productRepository.findProductEntityByNumber(mockValidProductEntity.getNumber()))
                .thenReturn(Optional.of(mockValidProductEntity));

        // Then
        final Product productDomainModelResponse = productService
                .getProductByNumber(mockValidProductEntity.getNumber());

        Assertions.assertEquals(
                productDomainModelResponse.getId(),
                mockValidProductEntity.getId()
        );

        Assertions.assertEquals(
                productDomainModelResponse.getName(),
                mockValidProductEntity.getName()
        );

        Assertions.assertEquals(
                productDomainModelResponse.getNumber(),
                mockValidProductEntity.getNumber()
        );

        Assertions.assertEquals(
                productDomainModelResponse.getPrice(),
                mockValidProductEntity.getPrice()
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findProductEntityByNumber(mockValidProductEntity.getNumber());
    }

    @Test
    void givenNonExistProductEntity_whenGetProductByNumber_thenThrowProductNotFoundException() {
        // Given
        final Long mockProductNumber = 2005L;

        // When
        Mockito.when(productRepository.findProductEntityByNumber(mockProductNumber))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                ProductNotFoundException.class,
                () -> productService.getProductByNumber(mockProductNumber)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).findProductEntityByNumber(mockProductNumber);
    }
}
