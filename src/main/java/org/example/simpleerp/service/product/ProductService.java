package org.example.simpleerp.service.product;

import org.example.simpleerp.common.model.CustomPage;
import org.example.simpleerp.common.model.dto.CustomPagingRequest;
import org.example.simpleerp.model.Product;

public interface ProductService {
    Product getProductById(
            final String productId
    );

    CustomPage<Product> getProducts(
            final CustomPagingRequest customPagingRequest
    );
}
