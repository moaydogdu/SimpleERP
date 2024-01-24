package org.example.simpleerp.model.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatisticsResponse {
    private String productId;
    private Long productNumber;
    private BigDecimal unitPrice;
    private BigDecimal totalSaleAmount;
    private BigDecimal totalPrice;
    private BigDecimal averageUnitPrice;
}
