package org.example.simpleerp.service.order.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.order.OrderNotFoundException;
import org.example.simpleerp.model.entity.OrderEntity;
import org.example.simpleerp.repository.OrderRepository;
import org.example.simpleerp.service.order.OrderDeleteService;
import org.example.simpleerp.service.order_product.OrderProductDeleteService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDeleteServiceImpl implements OrderDeleteService {

    private final OrderRepository orderRepository;
    private final OrderProductDeleteService orderProductDeleteService;

    @Override
    @Transactional
    public void deleteOrderById(
            final String orderId
    ) {
        final OrderEntity orderEntityFromDBToBeDelete = orderRepository
                .findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        if (orderEntityFromDBToBeDelete.getOrderProductEntities().isEmpty()){
            orderRepository.delete(orderEntityFromDBToBeDelete);
        }

        orderEntityFromDBToBeDelete.getOrderProductEntities()
                .forEach(
                        orderProductEntity -> orderProductDeleteService
                                .deleteOrderProductById(
                                        orderProductEntity.getId()
                                )
                );

        orderRepository.delete(orderEntityFromDBToBeDelete);

    }
}
