package org.example.simpleerp.service.order_product.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.order_product.OrderProductNotFoundException;
import org.example.simpleerp.model.entity.OrderProductEntity;
import org.example.simpleerp.repository.OrderProductRepository;
import org.example.simpleerp.service.order_product.OrderProductDeleteService;
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
