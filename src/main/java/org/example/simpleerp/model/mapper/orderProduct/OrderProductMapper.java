package org.example.simpleerp.model.mapper.orderProduct;

import org.example.simpleerp.model.OrderProduct;
import org.example.simpleerp.model.entity.OrderProductEntity;

public class OrderProductMapper {

    public static OrderProduct toDomainModel(
            final OrderProductEntity orderProductEntity
    ) {
        return OrderProduct.builder()
                .id(orderProductEntity.getId())
                .productNumber(orderProductEntity.getProduct().getNumber())
                .amount(orderProductEntity.getAmount())
                .unitPrice(orderProductEntity.getUnitPrice())
                .totalPrice(orderProductEntity.getTotalPrice())
                .build();
    }

}
