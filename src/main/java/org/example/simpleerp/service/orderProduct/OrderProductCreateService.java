package org.example.simpleerp.service.orderProduct;

import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.OrderProduct;
import org.example.simpleerp.model.dto.orderProduct.request.OrderProductRequest;

public interface OrderProductCreateService {
    OrderProduct createOrderProduct(
            final Order order,
            final OrderProductRequest orderProductRequest
    );
}
