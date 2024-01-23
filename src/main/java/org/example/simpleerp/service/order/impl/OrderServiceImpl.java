package org.example.simpleerp.service.order.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.order.OrderNotFoundException;
import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.entity.OrderEntity;
import org.example.simpleerp.model.mapper.order.OrderMapper;
import org.example.simpleerp.repository.OrderRepository;
import org.example.simpleerp.service.order.OrderService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrderById(
            final String orderId
    ) {
        final OrderEntity orderEntityFromDB = orderRepository
                .findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        return OrderMapper.toDomainModel(orderEntityFromDB);
    }
}
