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
public class ProductStatisticsByOrderResponse {
    private Long orderNumber;
    private Long productNumber;
    private BigDecimal productSaleAmount;
}
