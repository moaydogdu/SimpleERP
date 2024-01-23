package org.example.simpleerp.service.order;

import org.example.simpleerp.common.model.CustomPage;
import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.dto.order.request.OrderPagingRequest;

public interface OrderService {
    Order getOrderById(
            final String orderId
    );

    CustomPage<Order> getAllOrders(
            final OrderPagingRequest orderPagingRequest
    );
}
