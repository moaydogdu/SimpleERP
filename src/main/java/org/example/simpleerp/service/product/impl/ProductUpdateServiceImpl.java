package org.example.simpleerp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.product.ProductAlreadyExistException;
import org.example.simpleerp.exception.product.ProductNotFoundException;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.product.request.ProductUpdateRequest;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.model.mapper.product.ProductDTOMapper;
import org.example.simpleerp.model.mapper.product.ProductMapper;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.product.ProductUpdateService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdateService {

    private final ProductRepository productRepository;

    @Override
    public Product updateProduct(
            final String productId,
            final ProductUpdateRequest productUpdateRequest
    ) {
        final ProductEntity productEntityFromDBToBeUpdate = productRepository
                .findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        if (Boolean.FALSE.equals(
                productUpdateRequest.getNumber().equals(productEntityFromDBToBeUpdate.getNumber()))
        )
        {
            this.checkUniquenessOfProductNumber(productUpdateRequest.getNumber());
        }

        ProductDTOMapper.mapForUpdating(
                productEntityFromDBToBeUpdate,
                productUpdateRequest
        );

        productRepository.save(productEntityFromDBToBeUpdate);

        return ProductMapper
                .toDomainModel(productEntityFromDBToBeUpdate);
    }

    private void checkUniquenessOfProductNumber(
            final Long productNumber
    ) {
        if (productRepository.existsProductEntityByNumber(productNumber))
        {
            throw new ProductAlreadyExistException();
        }
    }


}
