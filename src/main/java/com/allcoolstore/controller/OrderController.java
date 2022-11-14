package com.allcoolstore.controller;

import com.allcoolstore.repository.OrderRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
