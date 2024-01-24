package org.example.simpleerp.service.order.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.simpleerp.common.util.PriceCalculator;
import org.example.simpleerp.exception.order.OrderAlreadyExistException;
import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.OrderProduct;
import org.example.simpleerp.model.dto.order.request.OrderCreateRequest;
import org.example.simpleerp.model.entity.OrderEntity;
import org.example.simpleerp.model.mapper.order.OrderDTOMapper;
import org.example.simpleerp.model.mapper.order.OrderMapper;
import org.example.simpleerp.repository.OrderRepository;
import org.example.simpleerp.service.order.OrderCreateService;
import org.example.simpleerp.service.order_product.OrderProductCreateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderCreateServiceImpl implements OrderCreateService {

    private final OrderRepository orderRepository;
    private final OrderProductCreateService orderProductCreateService;

    @Override
    @Transactional
    public Order createOrder(
            final OrderCreateRequest orderCreateRequest
    ) {
        this.checkUniquenessOfOrderNumber(orderCreateRequest.getNumber());

        OrderEntity orderEntityToBeSave = OrderDTOMapper
                .mapForSaving(orderCreateRequest);

        orderRepository.save(orderEntityToBeSave);

        Order orderDomainModel = OrderMapper
                .toDomainModel(orderEntityToBeSave);

        if (Objects.isNull(orderCreateRequest.getProducts()))
        {
            return orderDomainModel;
        }

        final List<OrderProduct> orderProducts = orderCreateRequest
                .getProducts()
                .stream()
                .map(orderProductRequest -> orderProductCreateService
                        .createOrderProduct(
                                orderDomainModel,
                                orderProductRequest
                        )
                )
                .toList();

        orderDomainModel.setOrderProducts(orderProducts);

        this.setTotalPriceOfOrderEntity(orderEntityToBeSave,orderProducts);

        orderDomainModel.setTotalPrice(orderEntityToBeSave.getTotalPrice());

        orderRepository.save(orderEntityToBeSave);

        return orderDomainModel;
    }

    private void checkUniquenessOfOrderNumber(
            final Long orderNumber
    ) {
        if (orderRepository.existsOrderEntityByNumber(orderNumber))
        {
            throw new OrderAlreadyExistException("There is another Order with given number.");
        }
    }

    private void setTotalPriceOfOrderEntity(
            final OrderEntity orderEntity,
            final List<OrderProduct> orderProducts
    ) {
        orderEntity.setTotalPrice(
                PriceCalculator.calculateTotalPriceOfOrder(orderProducts)
        );
    }
}
