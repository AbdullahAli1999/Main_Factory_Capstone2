package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Api.ApiResponse;
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
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    //GET
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //ADD
    public boolean addOrder(Order order){
        User uid = userRepository.findUserById(order.getUser_id());
        if(uid.getId().equals(order.getUser_id())){
            uid.setTotalOrders(uid.getTotalOrders() + 1);
            userRepository.save(uid);
            order.setOrder_date(LocalDate.now());
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
    //4.place order
    public void placeOrder(Integer userId, Integer productId, String coupon){
        User user = userRepository.findUserById(userId);
        Product product = productRepository.findProductById(productId);
        int price = product.getPrice();
        User rightCoupon = userRepository.takeCoupon(coupon);
        if(coupon != null && coupon.length() == 5){
            price = (int) (price * 0.9);
        }

        Order order = new Order();
        order.setStatus("PENDING");
        order.setUser_id(userId);
        orderRepository.save(order);

        OrderDetails details = new OrderDetails();
        details.setOrder_id(order.getId());
        details.setProduct_id(productId);
        details.setPrice(price);
        details.setQuantity(1);
        orderDetailsRepository.save(details);
    }

    //5.Return Order
    public Order returnOrder(Integer orderId){
        Order order = orderRepository.findOrderById(orderId);
        if (order == null){
            return null;
        }
        String status = order.getStatus();
        if("SHIPPED".equalsIgnoreCase(status) || "PENDING".equalsIgnoreCase(status) || "PROCESSING".equalsIgnoreCase(status)){
            order.setStatus("RETURNED");
            orderRepository.save(order);
            return order;
        }else if("RETURNED".equalsIgnoreCase(status)){
            return order;
        }else {
            return null;
        }
    }

    //7. get orders by status
    public List<Order> getOrderStatus(String status){
        return orderRepository.findOrderByStatus(status);

    }

    //8. Cancel order if not shipped
    public boolean cancelOrder(Integer id){
        Order order = orderRepository.findOrderById(id);
        if(order == null || order.getStatus().equalsIgnoreCase("SHIPPED") || order.getStatus().equalsIgnoreCase("RETURNED")){
    return false;
        }
        order.setStatus("CANCELLED");
        orderRepository.save(order);
        return true;
    }
}
