package org.example.simpleerp.controller;

import lombok.RequiredArgsConstructor;
import org.example.simpleerp.model.dto.statistics.ProductStatisticsByOrderResponse;
import org.example.simpleerp.model.dto.statistics.ProductStatisticsResponse;
import org.example.simpleerp.service.statistics.order.OrderStatisticsService;
import org.example.simpleerp.service.statistics.product.ProductStatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@Validated
public class StatisticsController {

    private final OrderStatisticsService orderStatisticsService;
    private final ProductStatisticsService productStatisticsService;

    @GetMapping("/get-total-price-of-all-orders")
    public ResponseEntity<String> getTotalPriceOfAllOrders()
    {
        final BigDecimal totalAmountOfAllOrders = orderStatisticsService
                .getTotalPriceOfAllOrders();

        return ResponseEntity.ok("Tüm siparişlerin toplam tutarı: "
                + totalAmountOfAllOrders);
    }

    @GetMapping("/get-product-statistics/{productNumber}")
    public ResponseEntity<ProductStatisticsResponse> getProductStatisticsByProductNumber(
            @PathVariable("productNumber") final Long productNumber
    ) {
        final ProductStatisticsResponse productStatisticsResponse = productStatisticsService
                .getProductStatisticsByNumber(productNumber);

        return ResponseEntity.ok(productStatisticsResponse);
    }

    @GetMapping("/get-product-statistics")
    public ResponseEntity<List<ProductStatisticsResponse>> getProductStatisticsOfAllProducts()
    {
        final List<ProductStatisticsResponse> productStatisticsResponseList =
                productStatisticsService.getProductStatisticsOfAllProducts();

        return ResponseEntity.ok(productStatisticsResponseList);
    }

    @GetMapping("/product-quantities-by-order/product-number/{productNumber}")
    public ResponseEntity<List<ProductStatisticsByOrderResponse>> getProductQuantitiesByOrder(
            @PathVariable("productNumber") final Long productNumber
    ) {
        final List<ProductStatisticsByOrderResponse> productStatisticsByOrderResponses =
                productStatisticsService.getProductQuantitiesByOrder(productNumber);

        return ResponseEntity.ok(productStatisticsByOrderResponses);
    }
}
