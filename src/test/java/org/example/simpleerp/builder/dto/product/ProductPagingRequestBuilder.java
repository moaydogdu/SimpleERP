package org.example.simpleerp.builder.dto.product;

import org.example.simpleerp.builder.BaseBuilder;
import org.example.simpleerp.builder.dto.CustomPagingBuilder;
import org.example.simpleerp.common.model.CustomPaging;
import org.example.simpleerp.model.dto.product.request.ProductPagingRequest;

public class ProductPagingRequestBuilder extends BaseBuilder<ProductPagingRequest> {

    public ProductPagingRequestBuilder() {
        super(ProductPagingRequest.class);
    }

    public ProductPagingRequestBuilder withValidFields() {
        return this.withPagination(new CustomPagingBuilder().withValidFields().build());
    }

    public ProductPagingRequestBuilder withPagination(
            final CustomPaging customPaging
    ) {
        this.data.setPagination(customPaging);
        return this;
    }

    @Override
    public ProductPagingRequest build() {
        return super.build();
    }
}
