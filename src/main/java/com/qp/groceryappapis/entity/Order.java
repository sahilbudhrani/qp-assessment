package com.qp.groceryappapis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    @Column(nullable = false)
    private boolean billPaid;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_list"
    )
    private List<OrderItem> orderItems = new ArrayList<>();
    @ManyToOne
    private User user;
}
