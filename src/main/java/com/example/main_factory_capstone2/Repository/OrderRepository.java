package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findOrderById(Integer id);
    List<Order> findOrderByStatus(String status);
}
