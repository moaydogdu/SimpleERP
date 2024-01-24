package org.example.simpleerp.service.product;

import org.example.simpleerp.base.BaseServiceTest;
import org.example.simpleerp.builder.dto.product.ProductCreateRequestBuilder;
import org.example.simpleerp.exception.product.ProductAlreadyExistException;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.product.request.ProductCreateRequest;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.product.impl.ProductCreateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ProductCreateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private ProductCreateServiceImpl productCreateService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void givenValidProductCreateRequest_whenCreateProduct_thenCreateProductAndReturnProductDomainModel()
    {
        // Given
        final ProductCreateRequest mockValidProductCreateRequest = new ProductCreateRequestBuilder()
                .withValidFields()
                .build();


        // When
        Mockito.when(productRepository.existsProductEntityByNumber(mockValidProductCreateRequest.getNumber()))
                .thenReturn(false);

        // Then
        final Product createdProduct = productCreateService
                .createProduct(mockValidProductCreateRequest);

        Assertions.assertEquals(
                createdProduct.getName(),
                mockValidProductCreateRequest.getName()
        );

        Assertions.assertEquals(
                createdProduct.getNumber(),
                mockValidProductCreateRequest.getNumber()
        );

        Assertions.assertEquals(
                createdProduct.getPrice(),
                mockValidProductCreateRequest.getPrice()
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).existsProductEntityByNumber(mockValidProductCreateRequest.getNumber());

        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).save(Mockito.any(ProductEntity.class));
    }

    @Test
    void givenInvalidProductCreateRequest_whenCreateProduct_thenThrowProductAlreadyExistException()
    {
        // Given
        final ProductCreateRequest mockValidProductCreateRequest = new ProductCreateRequestBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(productRepository.existsProductEntityByNumber(mockValidProductCreateRequest.getNumber()))
                .thenReturn(true);

        // Then
        Assertions.assertThrowsExactly(
                ProductAlreadyExistException.class,
                () -> productCreateService.createProduct(mockValidProductCreateRequest)
        );

        // Verify
        Mockito.verify(
                productRepository,
                Mockito.times(0)
        ).save(Mockito.any(ProductEntity.class));

        Mockito.verify(
                productRepository,
                Mockito.times(1)
        ).existsProductEntityByNumber(mockValidProductCreateRequest.getNumber());

    }

}
