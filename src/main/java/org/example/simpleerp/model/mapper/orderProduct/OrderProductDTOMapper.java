package org.example.simpleerp.model.mapper.orderProduct;

import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.order.OrderProductRequest;
import org.example.simpleerp.model.entity.OrderProductEntity;
import org.example.simpleerp.model.mapper.order.OrderMapper;
import org.example.simpleerp.model.mapper.product.ProductMapper;

public class OrderProductDTOMapper {

    public static OrderProductEntity mapForSaving(
            final Order order,
            final Product product,
            final OrderProductRequest orderProductRequest
    ) {
        return OrderProductEntity.builder()
                .order(
                        OrderMapper.toEntity(order)
                )
                .product(
                        ProductMapper.toEntity(product)
                )
                .amount(orderProductRequest.getAmount())
                .unitPrice(
                        orderProductRequest.getUnitPrice() == null ?
                                product.getPrice() :
                                orderProductRequest.getUnitPrice()
                )
                .build();
    }
}
