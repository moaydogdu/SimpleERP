package org.example.simpleerp.model;

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
public class OrderProduct {
    private String id;
    private Long productNumber;
    private BigDecimal amount;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
