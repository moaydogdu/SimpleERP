package org.example.simpleerp.service.orderProduct.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.orderProduct.OrderProductNotFoundException;
import org.example.simpleerp.model.entity.OrderProductEntity;
import org.example.simpleerp.repository.OrderProductRepository;
import org.example.simpleerp.service.orderProduct.OrderProductDeleteService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductDeleteServiceImpl implements OrderProductDeleteService {

    private final OrderProductRepository orderProductRepository;

    @Override
    public void deleteOrderProductById(
            final String orderProductId
    ) {
        final OrderProductEntity orderProductEntityFromDBToBeDelete =
                orderProductRepository.findById(orderProductId)
                        .orElseThrow(OrderProductNotFoundException::new);

        orderProductRepository.delete(orderProductEntityFromDBToBeDelete);
    }
}
