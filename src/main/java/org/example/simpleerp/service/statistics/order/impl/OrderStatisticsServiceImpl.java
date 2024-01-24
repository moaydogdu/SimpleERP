package org.example.simpleerp.service.statistics.order.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.statistics.UnableToCalculateStatisticsException;
import org.example.simpleerp.model.entity.OrderEntity;
import org.example.simpleerp.repository.OrderRepository;
import org.example.simpleerp.service.statistics.order.OrderStatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatisticsServiceImpl implements OrderStatisticsService {

    private final OrderRepository orderRepository;

    /**
     * This approach is faster. But for the case we are not using it.
     */
    @Override
    public BigDecimal getTotalPriceOfAllOrders() {
        return orderRepository.getTotalPriceSum();
    }

    @Override
    public BigDecimal getTotalPriceOfAllOrdersWithJavaCode() {
        final List<OrderEntity> orderEntitiesFromDB = orderRepository
                .findAll();

        if (orderEntitiesFromDB.isEmpty())
        {
            throw new UnableToCalculateStatisticsException("Couldn't find any order!");
        }

        return orderEntitiesFromDB.stream()
                .map(OrderEntity::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
