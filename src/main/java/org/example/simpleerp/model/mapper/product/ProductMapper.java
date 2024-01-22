package org.example.simpleerp.model.mapper.product;

import org.example.simpleerp.common.model.CustomPage;
import org.example.simpleerp.common.model.dto.CustomPagingResponse;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.entity.ProductEntity;

import java.util.List;

public class ProductMapper {

    public static Product toDomainModel(
            final ProductEntity productEntity
    ) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .number(productEntity.getNumber())
                .price(productEntity.getPrice())
                .build();
    }

    public static List<Product> toDomainModel(
            final List<ProductEntity> productEntities
    ) {
        return productEntities.stream()
                .map(ProductMapper::toDomainModel)
                .toList();
    }

    public static CustomPagingResponse<Product> toCustomPagingResponse(
            final CustomPage<Product> customProductPage
    ) {
        return CustomPagingResponse.<Product>builder()
                .of(customProductPage)
                .content(
                        customProductPage.getContent()
                )
                .build();
    }
}
