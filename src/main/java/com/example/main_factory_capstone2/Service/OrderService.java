package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.Order;
import com.example.main_factory_capstone2.Model.OrderDetails;
import com.example.main_factory_capstone2.Model.Product;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Repository.OrderRepository;
import com.example.main_factory_capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    //GET
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //ADD
    public boolean addOrder(Order order){
        User uid = userRepository.findUserById(order.getUser_id());
        if(uid.getId().equals(order.getUser_id())){
            orderRepository.save(order);
            return true;
        }
        return false;
    }
    //UPDATE
    public boolean updateOrder(Integer id , Order order){
        Order oldOrder = orderRepository.findOrderById(id);
        if(oldOrder == null){
            return false;
        }
        oldOrder.setOrder_date(LocalDate.now());
        oldOrder.setStatus(order.getStatus());
        orderRepository.save(oldOrder);
        return true;
    }
    //DELETE
    public boolean deleteOrder(Integer id){
        Order delOrder = orderRepository.findOrderById(id);
        if(delOrder == null){
            return false;
        }
        orderRepository.delete(delOrder);
        return true;
    }
}
