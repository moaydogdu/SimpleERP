package org.example.simpleerp.model.dto.order.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.simpleerp.model.dto.orderProduct.request.OrderProductRequest;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
    @NotNull
    private Long number;

    @Valid
    private List<OrderProductRequest> products;
}
