package org.example.simpleerp.service.order;

import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.dto.order.request.OrderUpdateRequest;

public interface OrderUpdateService {
    Order updateOrder(
            final String orderId,
            final OrderUpdateRequest orderUpdateRequest
    );
}
