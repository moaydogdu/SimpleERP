package org.example.simpleerp.builder.dto.product;

import com.github.javafaker.Faker;
import org.example.simpleerp.builder.BaseBuilder;
import org.example.simpleerp.model.dto.product.request.ProductUpdateRequest;

import java.math.BigDecimal;

public class ProductUpdateRequestBuilder extends BaseBuilder<ProductUpdateRequest> {
    public ProductUpdateRequestBuilder() {
        super(ProductUpdateRequest.class);
    }

    public ProductUpdateRequestBuilder withValidField()
    {
        final Faker faker = new Faker();

        return this.withName(faker.book().title())
                .withNumber(faker.number().numberBetween(2000L,2100L))
                .withPrice(BigDecimal.valueOf(faker.number().numberBetween(25L,300L)));
    }

    public ProductUpdateRequestBuilder withName(
            final String name
    ) {
        this.data.setName(name);
        return this;
    }

    public ProductUpdateRequestBuilder withNumber(
            final Long number
    ) {
        this.data.setNumber(number);
        return this;
    }

    public ProductUpdateRequestBuilder withPrice(
            final BigDecimal price
    ) {
        this.data.setPrice(price);
        return this;
    }

    @Override
    public ProductUpdateRequest build() {
        return super.build();
    }
}
