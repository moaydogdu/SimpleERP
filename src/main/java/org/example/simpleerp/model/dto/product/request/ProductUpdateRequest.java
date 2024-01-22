package org.example.simpleerp.model.dto.product.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
public class ProductUpdateRequest {
    @NotBlank
    private String name;
    @NotNull
    private Long number;
    @DecimalMin(value = "0.0001")
    private BigDecimal price;
}
