package org.example.simpleerp.service.order;

import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.OrderProduct;
import org.example.simpleerp.model.dto.order.OrderProductRequest;

public interface OrderProductCreateService {
    OrderProduct createOrderProduct(
            final Order order,
            final OrderProductRequest orderProductRequest
    );
}
