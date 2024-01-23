package org.example.simpleerp.service.order;


import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.dto.order.request.OrderCreateRequest;

public interface OrderCreateService {
    Order createOrder(
            final OrderCreateRequest orderCreateRequest
    );
}
