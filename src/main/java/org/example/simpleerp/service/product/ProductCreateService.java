package org.example.simpleerp.service.product;

import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.product.request.ProductCreateRequest;

public interface ProductCreateService {
    Product createProduct(
            final ProductCreateRequest productCreateRequest
    );
}
