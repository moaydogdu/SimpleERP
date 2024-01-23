package org.example.simpleerp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.simpleerp.model.Order;
import org.example.simpleerp.model.dto.order.request.OrderCreateRequest;
import org.example.simpleerp.service.order.OrderCreateService;
import org.example.simpleerp.service.order.OrderService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;
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

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable("orderId") @UUID final String orderId
    ) {
        final Order orderFromDB = orderService
                .getOrderById(orderId);

        return ResponseEntity.ok(orderFromDB);
    }


}
