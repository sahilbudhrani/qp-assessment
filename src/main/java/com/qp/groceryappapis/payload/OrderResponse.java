package com.qp.groceryappapis.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String id;
    private String name;
    private String email;
    private String address;
    private boolean billPaid;
    private List<OrderItemDto> orderItems;
}
