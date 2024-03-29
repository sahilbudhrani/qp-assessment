package com.qp.groceryappapis.repository;

import com.qp.groceryappapis.entity.Order;
import com.qp.groceryappapis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUser(User user);
}
