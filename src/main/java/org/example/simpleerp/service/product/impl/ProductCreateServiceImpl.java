package org.example.simpleerp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.product.ProductAlreadyExistException;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.product.request.ProductCreateRequest;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.model.mapper.product.ProductDTOMapper;
import org.example.simpleerp.model.mapper.product.ProductMapper;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.product.ProductCreateService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateServiceImpl implements ProductCreateService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(
            final ProductCreateRequest productCreateRequest
    ) {
        this.checkUniquenessOfProductNumber(productCreateRequest.getNumber());

        ProductEntity productEntityToBeSave = ProductDTOMapper
                .mapForSaving(productCreateRequest);

        productRepository.save(productEntityToBeSave);

        return ProductMapper.toDomainModel(productEntityToBeSave);
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
