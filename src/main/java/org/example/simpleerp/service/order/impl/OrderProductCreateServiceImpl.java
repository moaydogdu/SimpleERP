package org.example.simpleerp.service.order.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.common.util.PriceCalculator;
import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.OrderProduct;
import org.example.simpleerp.model.Product;
import org.example.simpleerp.model.dto.order.OrderProductRequest;
import org.example.simpleerp.model.entity.OrderProductEntity;
import org.example.simpleerp.model.mapper.orderProduct.OrderProductDTOMapper;
import org.example.simpleerp.model.mapper.orderProduct.OrderProductMapper;
import org.example.simpleerp.repository.OrderProductRepository;
import org.example.simpleerp.service.order.OrderProductCreateService;
import org.example.simpleerp.service.product.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductCreateServiceImpl implements OrderProductCreateService {

    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;

    @Override
    public OrderProduct createOrderProduct(
            final Order order,
            final OrderProductRequest orderProductRequest
    ) {
        Product productEntityFromDbForCreateOrder = productService
                .getProductByNumber(orderProductRequest.getProductNumber());

        OrderProductEntity orderProductEntity = OrderProductDTOMapper
                .mapForSaving(
                        order,
                        productEntityFromDbForCreateOrder,
                        orderProductRequest
                );

        this.calculateTotalPriceOfOrderProductEntity(orderProductEntity);

        orderProductRepository.save(orderProductEntity);

        return OrderProductMapper
                .toDomainModel(orderProductEntity);
    }

    private void calculateTotalPriceOfOrderProductEntity(
            final OrderProductEntity orderProductEntity
    ) {
        orderProductEntity.setTotalPrice(
                PriceCalculator.calculateTotalPriceOfOrderProduct(
                        orderProductEntity.getAmount(),
                        orderProductEntity.getUnitPrice()
                )
        );
    }
}
