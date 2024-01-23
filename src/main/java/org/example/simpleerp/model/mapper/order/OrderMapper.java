package org.example.simpleerp.model.mapper.order;

import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.entity.OrderEntity;

public class OrderMapper {
    public static Order toDomainModel(
            final OrderEntity orderEntity
    ) {
        return Order.builder()
                .id(orderEntity.getId())
                .number(orderEntity.getNumber())
                .totalPrice(orderEntity.getTotalPrice())
                .build();
    }

    public static OrderEntity toEntity(
            final Order order
    ) {
        return OrderEntity.builder()
                .id(order.getId())
                .number(order.getNumber())
                .build();

    }
}
