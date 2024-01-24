package org.example.simpleerp.builder.entity;

import com.github.javafaker.Faker;
import org.example.simpleerp.builder.BaseBuilder;
import org.example.simpleerp.model.entity.OrderProductEntity;
import org.example.simpleerp.model.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProductEntityBuilder extends BaseBuilder<ProductEntity> {
    public ProductEntityBuilder() {
        super(ProductEntity.class);
    }

    public ProductEntityBuilder withValidFields() {
        final Faker faker = new Faker();
        return this.withId(UUID.randomUUID().toString())
                .withName(faker.book().title())
                .withNumber(faker.number().numberBetween(2000L, 2100L))
                .withPrice(BigDecimal.valueOf(faker.number().numberBetween(25L, 300L)))
                .withOrderProductEntities(List.of(
                        OrderProductEntity.builder().id(UUID.randomUUID().toString()).build(),
                        OrderProductEntity.builder().id(UUID.randomUUID().toString()).build()
                ));
    }

    public ProductEntityBuilder withId(
            final String productId
    ) {
        this.data.setId(productId);
        return this;
    }

    public ProductEntityBuilder withName(
            final String name
    ) {
        this.data.setName(name);
        return this;
    }

    public ProductEntityBuilder withNumber(
            final Long number
    ) {
        this.data.setNumber(number);
        return this;
    }

    public ProductEntityBuilder withPrice(
            final BigDecimal price
    ) {
        this.data.setPrice(price);
        return this;
    }

    public ProductEntityBuilder withOrderProductEntity(
            final OrderProductEntity orderProductEntity
    ) {
        if (Objects.isNull(this.data.getOrderProductEntities()))
        {
            this.data.setOrderProductEntities(List.of(orderProductEntity));
            return this;
        }
        this.data.getOrderProductEntities().add(orderProductEntity);
        return this;
    }

    public ProductEntityBuilder withOrderProductEntities(
            final List<OrderProductEntity> orderProductEntities
    ) {
        this.data.setOrderProductEntities(orderProductEntities);
        return this;
    }

    @Override
    public ProductEntity build() {
        return super.build();
    }
}
