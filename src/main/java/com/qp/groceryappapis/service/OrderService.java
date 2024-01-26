package com.qp.groceryappapis.service;

import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.OrderRequest;
import com.qp.groceryappapis.payload.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);
    List<OrderResponse> getUserOrders() throws ResourceNotFoundException;
    List<OrderResponse> getAllOrders();
    void cancelOrder(String id) throws ResourceNotFoundException;
}
