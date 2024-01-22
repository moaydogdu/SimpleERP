package org.example.simpleerp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.common.model.CustomPage;
import org.example.simpleerp.common.model.dto.CustomPagingRequest;
import org.example.simpleerp.exception.product.ProductNotFoundException;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.model.mapper.product.ProductMapper;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProductById(
            final String productId
    ) {
        final ProductEntity productEntityFromDB = productRepository
                .findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        return ProductMapper.toDomainModel(productEntityFromDB);
    }

    @Override
    public CustomPage<Product> getProducts(
           final CustomPagingRequest customPagingRequest
    ) {
        final Page<ProductEntity> productEntityPage = productRepository
                .findAll(customPagingRequest.toPageable());

        if (productEntityPage.getContent().isEmpty())
        {
            throw new ProductNotFoundException();
        }

        final List<Product> productDomainModels = ProductMapper
                .toDomainModel(productEntityPage.getContent());

        return CustomPage.of(
                productDomainModels,
                productEntityPage
        );
    }
}
