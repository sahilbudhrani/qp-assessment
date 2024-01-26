package com.qp.groceryappapis.controller;

import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.OrderRequest;
import com.qp.groceryappapis.payload.OrderResponse;
import com.qp.groceryappapis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('NORMAL')")
    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.createOrder(orderRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/myOrders")
    public ResponseEntity<List<OrderResponse>> getUserOrders() throws ResourceNotFoundException, ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getUserOrders());
    }

    @PreAuthorize("hasRole('NORMAL')")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable("orderId") String id) throws ResourceNotFoundException {
        orderService.cancelOrder(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Order Cancelled Successfully", HttpStatus.OK));
    }
}
