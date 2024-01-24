package org.example.simpleerp.model.dto.order_product.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductRequest {
    @NotNull
    private Long productNumber;
    @DecimalMin(value = "0.0001")
    private BigDecimal amount;
    private BigDecimal unitPrice;
}
