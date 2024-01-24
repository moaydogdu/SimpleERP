package org.example.simpleerp.builder.dto.product;

import com.github.javafaker.Faker;
import org.example.simpleerp.builder.BaseBuilder;
import org.example.simpleerp.model.dto.product.request.ProductCreateRequest;

import java.math.BigDecimal;

public class ProductCreateRequestBuilder extends BaseBuilder<ProductCreateRequest> {
    public ProductCreateRequestBuilder() {
        super(ProductCreateRequest.class);
    }

    public ProductCreateRequestBuilder withValidFields()
    {
        final Faker faker = new Faker();
        return this.withName(faker.book().title())
                .withNumber(faker.number().numberBetween(2000L,2100L))
                .withPrice(BigDecimal.valueOf(faker.number().numberBetween(25,300)));
    }

    public ProductCreateRequestBuilder withName(
            final String name
    ) {
        this.data.setName(name);
        return this;
    }

    public ProductCreateRequestBuilder withNumber(
            final Long number
    ) {
        this.data.setNumber(number);
        return this;
    }

    public ProductCreateRequestBuilder withPrice(
            final BigDecimal price
    ) {
        this.data.setPrice(price);
        return this;
    }

    @Override
    public ProductCreateRequest build() {
        return super.build();
    }
}
