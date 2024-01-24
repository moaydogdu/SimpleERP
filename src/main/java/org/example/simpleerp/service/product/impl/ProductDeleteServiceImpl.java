package org.example.simpleerp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.product.ProductNotFoundException;
import org.example.simpleerp.exception.product.UnableToDeleteProductException;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.product.ProductDeleteService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleteServiceImpl implements ProductDeleteService {

    private final ProductRepository productRepository;

    @Override
    public void deleteProductById(
            final String productId
    ) {
        ProductEntity productEntityToBeDelete = productRepository
                .findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        if (Boolean.FALSE.equals(
                productEntityToBeDelete.getOrderProductEntities().isEmpty())
        ) {
            throw new UnableToDeleteProductException(
                    "Because its related with some Orders!"
            );
        }

        productRepository.delete(productEntityToBeDelete);
    }
}
