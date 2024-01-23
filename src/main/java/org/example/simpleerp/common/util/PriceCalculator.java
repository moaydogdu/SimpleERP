package org.example.simpleerp.common.util;

import org.example.simpleerp.model.OrderProduct;

import java.math.BigDecimal;
import java.util.List;

public class PriceCalculator {
    public static BigDecimal calculateTotalPriceOfOrderProduct(
            final BigDecimal amount,
            final BigDecimal unitPrice
    ) {
        return amount.multiply(unitPrice);
    }

    public static BigDecimal calculateTotalPriceOfOrder(
            final List<OrderProduct> orderProducts
    ) {
        return orderProducts.stream()
                .map(OrderProduct::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
