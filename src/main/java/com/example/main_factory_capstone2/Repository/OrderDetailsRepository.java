package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
    OrderDetails findOrderDetailsById(Integer id);
}
