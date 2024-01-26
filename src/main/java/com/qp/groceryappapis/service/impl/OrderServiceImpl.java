package com.qp.groceryappapis.service.impl;

import com.qp.groceryappapis.entity.Item;
import com.qp.groceryappapis.entity.Order;
import com.qp.groceryappapis.entity.OrderItem;
import com.qp.groceryappapis.entity.User;
import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.OrderRequest;
import com.qp.groceryappapis.payload.OrderResponse;
import com.qp.groceryappapis.repository.ItemRepository;
import com.qp.groceryappapis.repository.OrderItemRepository;
import com.qp.groceryappapis.repository.OrderRepository;
import com.qp.groceryappapis.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderRequest.getItems().forEach(
                (k,v) -> {
                    Item name = itemRepository.findByName(k).orElseThrow(() -> new ResourceNotFoundException("Item", "ID", k));
                    Integer availableInventory = name.getAvailableInventory();
                    if(availableInventory > 0 && availableInventory < v){
                        throw new ResourceNotFoundException("Item", k);
                    }
                    name.setAvailableInventory(availableInventory - v);
                    itemRepository.save(name);
                    OrderItem orderItem = OrderItem.builder().item(name).quantity(v).build();
                    orderItemList.add(orderItem);
                    orderItemRepository.save(orderItem);
                }
        );
        Order order = modelMapper.map(orderRequest, Order.class);
        order.getOrderItems().addAll(orderItemList);
        order.setUser(getCurrentUser());
        order.setId(UUID.randomUUID().toString());
        Order save = orderRepository.save(order);
        return modelMapper.map(save, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getUserOrders() throws ResourceNotFoundException {
        User user = getCurrentUser();
        List<Order> orders = orderRepository.findByUser(user);
        if(orders.isEmpty())
            throw new ResourceNotFoundException("Order", "User", user.getName());
        List<OrderResponse> orderDtos = orders.stream().map(order -> modelMapper.map(order, OrderResponse.class)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderDtos = orders.stream().map(order -> modelMapper.map(order, OrderResponse.class)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public void cancelOrder(String id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "ID", String.valueOf(id)));
        orderRepository.delete(order);
    }

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
