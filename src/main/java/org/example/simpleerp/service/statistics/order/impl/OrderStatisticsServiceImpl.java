package org.example.simpleerp.service.statistics.order.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.repository.OrderRepository;
import org.example.simpleerp.service.statistics.order.OrderStatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderStatisticsServiceImpl implements OrderStatisticsService {

    private final OrderRepository orderRepository;

    @Override
    public BigDecimal getTotalPriceOfAllOrders() {
        return orderRepository.getTotalPriceSum();
    }
}
