package org.example.simpleerp.model.mapper.product;

import org.example.simpleerp.model.dto.product.request.ProductCreateRequest;
import org.example.simpleerp.model.dto.product.request.ProductUpdateRequest;
import org.example.simpleerp.model.entity.ProductEntity;

public class ProductDTOMapper {

    public static ProductEntity mapForSaving(
            final ProductCreateRequest productCreateRequest
    ) {
        return ProductEntity.builder()
                .name(productCreateRequest.getName())
                .number(productCreateRequest.getNumber())
                .price(productCreateRequest.getPrice())
                .build();
    }

    public static void mapForUpdating(
            final ProductEntity productEntityFromDBToBeUpdate,
            final ProductUpdateRequest productUpdateRequest
    ) {
        productEntityFromDBToBeUpdate.setName(productUpdateRequest.getName());
        productEntityFromDBToBeUpdate.setNumber(productUpdateRequest.getNumber());
        productEntityFromDBToBeUpdate.setPrice(productUpdateRequest.getPrice());
    }
}
