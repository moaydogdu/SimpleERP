package org.example.simpleerp.service.statistics.product;

import org.example.simpleerp.model.dto.statistics.ProductStatisticsByOrderResponse;
import org.example.simpleerp.model.dto.statistics.ProductStatisticsResponse;

import java.util.List;

public interface ProductStatisticsService {
    ProductStatisticsResponse getProductStatisticsByNumber(
            final Long productNumber
    );

    List<ProductStatisticsResponse> getProductStatisticsOfAllProducts();

    List<ProductStatisticsByOrderResponse> getProductQuantitiesByOrder(
            final Long productNumber
    );
}
