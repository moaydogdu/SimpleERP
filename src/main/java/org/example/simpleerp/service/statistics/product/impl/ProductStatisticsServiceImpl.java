package org.example.simpleerp.service.statistics.product.impl;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.exception.order.OrderNotFoundException;
import org.example.simpleerp.exception.product.ProductNotFoundException;
import org.example.simpleerp.exception.statistics.UnableToCalculateStatisticsException;
import org.example.simpleerp.model.dto.statistics.ProductStatisticsByOrderResponse;
import org.example.simpleerp.model.dto.statistics.ProductStatisticsResponse;
import org.example.simpleerp.model.entity.OrderProductEntity;
import org.example.simpleerp.model.entity.ProductEntity;
import org.example.simpleerp.repository.ProductRepository;
import org.example.simpleerp.service.statistics.product.ProductStatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStatisticsServiceImpl implements ProductStatisticsService {

    private final ProductRepository productRepository;

    @Override
    public ProductStatisticsResponse getProductStatisticsByNumber(
            final Long productNumber
    ) {
        final ProductEntity productEntityFromDB = productRepository
                .findProductEntityByNumber(productNumber)
                .orElseThrow(ProductNotFoundException::new);

        if (productEntityFromDB.getOrderProductEntities().isEmpty()) {
            throw new UnableToCalculateStatisticsException("Because there is no sale record of this Product!");
        }

        final BigDecimal totalPriceOfProductSales = productEntityFromDB.getOrderProductEntities().stream()
                .map(OrderProductEntity::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal totalSaleAmount = productEntityFromDB.getOrderProductEntities().stream()
                .map(OrderProductEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal averagePriceOfProductSales = totalPriceOfProductSales.divide(
                totalSaleAmount, 4, RoundingMode.HALF_UP);

        return ProductStatisticsResponse.builder()
                .productId(productEntityFromDB.getId())
                .productNumber(productEntityFromDB.getNumber())
                .unitPrice(productEntityFromDB.getPrice())
                .totalSaleAmount(totalSaleAmount)
                .totalPrice(totalPriceOfProductSales)
                .averageUnitPrice(averagePriceOfProductSales)
                .build();
    }

    @Override
    public List<ProductStatisticsResponse> getProductStatisticsOfAllProducts() {

        final List<ProductEntity> productEntitiesFromDB = productRepository
                .findAllByOrderByNumberAsc();

        if (productEntitiesFromDB.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return productEntitiesFromDB.stream()
                .map(
                        productEntity ->
                                this.getProductStatisticsByNumber(
                                        productEntity.getNumber()
                                )
                )
                .toList();
    }

    @Override
    public List<ProductStatisticsByOrderResponse> getProductQuantitiesByOrder(
            final Long productNumber
    ) {
        final ProductEntity productEntityFromDB = productRepository
                .findProductEntityByNumber(productNumber)
                .orElseThrow(ProductNotFoundException::new);

        if (productEntityFromDB.getOrderProductEntities().isEmpty()) {
            throw new OrderNotFoundException("This product has no relation with any order.");
        }

        return productEntityFromDB.getOrderProductEntities().stream()
                .map(orderProductEntity ->
                        ProductStatisticsByOrderResponse.builder()
                                .orderNumber(orderProductEntity.getOrder().getNumber())
                                .productNumber(orderProductEntity.getProduct().getNumber())
                                .productSaleAmount(orderProductEntity.getAmount())
                                .build()
                )
                .toList();
    }
}
