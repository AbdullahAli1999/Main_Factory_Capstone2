package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.Order;
import com.example.main_factory_capstone2.Model.OrderDetails;
import com.example.main_factory_capstone2.Model.Product;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Repository.OrderDetailsRepository;
import com.example.main_factory_capstone2.Repository.OrderRepository;
import com.example.main_factory_capstone2.Repository.ProductRepository;
import com.example.main_factory_capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    //GET
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    //ADD
    public boolean addOrderDetails(OrderDetails orderDetails){
        Order oid = orderRepository.findOrderById(orderDetails.getOrder_id());
        Product pid = productRepository.findProductById(orderDetails.getProduct_id());
        if(oid.getId().equals(orderDetails.getOrder_id()) && pid.getId().equals(orderDetails.getProduct_id())){
            pid.setQuantity_in_stock(pid.getQuantity_in_stock() - orderDetails.getQuantity());
            productRepository.save(pid);
            orderDetails.setPrice(pid.getPrice());
            orderDetailsRepository.save(orderDetails);
            return true;
        }
        return false;
    }
    //UPDATE
    public boolean updateOrderDetails(Integer id , OrderDetails orderDetails){
        OrderDetails oldOrderDetails = orderDetailsRepository.findOrderDetailsById(id);
        if(orderDetails == null){
            return false;
        }
        oldOrderDetails.setQuantity(orderDetails.getQuantity());
        orderDetailsRepository.save(orderDetails);
        return true;
    }
    //DELETE
    public boolean deleteOrderDetails(Integer id){
        OrderDetails delOrderDetails = orderDetailsRepository.findOrderDetailsById(id);
        if(delOrderDetails == null){
            return false;
        }
        orderDetailsRepository.delete(delOrderDetails);
        return true;
    }
}
