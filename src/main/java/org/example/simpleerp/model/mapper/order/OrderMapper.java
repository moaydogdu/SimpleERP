package org.example.simpleerp.model.mapper.order;

import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.entity.OrderEntity;
import org.example.simpleerp.model.mapper.orderProduct.OrderProductMapper;

public class OrderMapper {
    public static Order toDomainModel(
            final OrderEntity orderEntity
    ) {
        return Order.builder()
                .id(orderEntity.getId())
                .number(orderEntity.getNumber())
                .totalPrice(orderEntity.getTotalPrice())
                .orderProducts(
                        OrderProductMapper.toDomainModel(orderEntity.getOrderProductEntities())
                )
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
