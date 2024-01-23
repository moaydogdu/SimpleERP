package org.example.simpleerp.service.order;

import org.example.simpleerp.model.Order;

public interface OrderService {
    Order getOrderById(
            final String orderId
    );
}
