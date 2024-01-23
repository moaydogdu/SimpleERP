package org.example.simpleerp.service.order.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.order.OrderAlreadyExistException;
import org.example.simpleerp.exception.order.OrderNotFoundException;
import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.dto.order.request.OrderUpdateRequest;
import org.example.simpleerp.model.entity.OrderEntity;
import org.example.simpleerp.model.mapper.order.OrderDTOMapper;
import org.example.simpleerp.model.mapper.order.OrderMapper;
import org.example.simpleerp.repository.OrderRepository;
import org.example.simpleerp.service.order.OrderUpdateService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderUpdateServiceImpl implements OrderUpdateService {

    private final OrderRepository orderRepository;

    @Override
    public Order updateOrder(
            final String orderId,
            final OrderUpdateRequest orderUpdateRequest
    ) {
        this.checkUniquenessOfOrderNumber(orderUpdateRequest.getNumber());

        final OrderEntity orderEntityFromDBToBeUpdate = orderRepository
                .findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        OrderDTOMapper.mapForUpdating(
                orderEntityFromDBToBeUpdate,
                orderUpdateRequest
        );

        orderRepository.save(orderEntityFromDBToBeUpdate);

        return OrderMapper.toDomainModel(orderEntityFromDBToBeUpdate);
    }

    private void checkUniquenessOfOrderNumber(
            final Long orderNumber
    ) {
        if (orderRepository.existsOrderEntityByNumber(orderNumber)){
            throw new OrderAlreadyExistException();
        }
    }
}
