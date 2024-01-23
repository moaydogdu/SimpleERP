package org.example.simpleerp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.dto.order.request.OrderCreateRequest;
import org.example.simpleerp.service.order.OrderCreateService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderCreateService orderCreateService;

    // TODO : GET Order
    // TODO : GETALL Order
    // TODO : UPDATE Order
    // TODO : DELETE Order

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody @Valid final OrderCreateRequest orderCreateRequest
    ) {
        final Order createdOrder = orderCreateService
                .createOrder(orderCreateRequest);

        return ResponseEntity.ok(createdOrder);
    }


}
