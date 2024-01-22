package org.example.simpleerp.service.product;

import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.product.request.ProductUpdateRequest;

public interface ProductUpdateService {
    Product updateProduct(
            final String productId,
            final ProductUpdateRequest productUpdateRequest
    );
}
