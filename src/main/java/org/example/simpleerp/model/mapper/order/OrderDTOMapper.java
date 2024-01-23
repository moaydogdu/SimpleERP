package org.example.simpleerp.model.mapper.order;

import org.example.simpleerp.model.dto.order.request.OrderCreateRequest;
import org.example.simpleerp.model.entity.OrderEntity;

import java.math.BigDecimal;

public class OrderDTOMapper {

    public static OrderEntity mapForSaving(
            final OrderCreateRequest orderCreateRequest
    ) {
        return OrderEntity.builder()
                .number(orderCreateRequest.getNumber())
                .totalPrice(BigDecimal.ZERO)
                .build();
    }
}
