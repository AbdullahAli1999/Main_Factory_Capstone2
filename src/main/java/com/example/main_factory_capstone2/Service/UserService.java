package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Api.ApiResponse;
import com.example.main_factory_capstone2.Model.Order;
import com.example.main_factory_capstone2.Model.OrderDetails;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Repository.OrderDetailsRepository;
import com.example.main_factory_capstone2.Repository.OrderRepository;
import com.example.main_factory_capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    //GET
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //ADD
    public void addUser(User user){
        user.setCreated_at(LocalDate.now());
        userRepository.save(user);
    }

    //UPDATE
    public boolean updateUser(Integer id , User user){
        User oldUser = userRepository.findUserById(id);
        if(oldUser == null){
            return false;
        }
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setCreated_at(LocalDate.now());
        userRepository.save(oldUser);
        return true;
    }

    //DELETE
    public boolean deleteUser(Integer id){
        User delUser = userRepository.findUserById(id);
        if(delUser == null){
            return false;
        }
        userRepository.delete(delUser);
        return true;
    }
    //1.Top buyers
    public List<User> getTopBuyers(){
        List<User> users = userRepository.findAll();
        List<User> topBuyers = new ArrayList<>();

        for(User user : users){
            if(user.getTotalOrders() > 0){
                topBuyers.add(user);
            }
        }
        for (int i = 0; i <topBuyers.size() ; i++) {
            for (int j = i+1; j < topBuyers.size() ; j++) {
                if(topBuyers.get(j).getTotalOrders() > topBuyers.get(i).getTotalOrders()){
                    User temp = topBuyers.get(i);
                    topBuyers.set(i, topBuyers.get(j));
                    topBuyers.set(j,temp);
                }

            }
        }
        if(topBuyers.size() > 5){
            return topBuyers.subList(0,5);
        }
        return topBuyers;
    }

    //3.CheckDiscount
    public String checkDiscount(Integer id){
        List<Order> orders = orderRepository.findAll();
        int totalQuantity = 0;

        for (Order order : orders){
            if(order.getUser_id().equals(id)){
                List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
                for (OrderDetails details : orderDetailsList){
                    if(details.getOrder_id().equals(order.getId())){
                        totalQuantity += details.getQuantity();
                    }
                }
            }
        }
        if(totalQuantity > 100){
            String coupon = generateCoupon();
            return "10% Active. Your Coupon code:  " + coupon ;
        }else {
            return "No Discount";
        }
    }
    public String generateCoupon(){
        Random random = new Random();
        String digitPart = "" + random.nextInt(10)+ random.nextInt(10) + random.nextInt(10);
        char Letter1 = (char) ('A' + random.nextInt(26));
        char Letter2 = (char) ('A' + random.nextInt(26));
        String letterPart = "" + Letter1 + Letter2;
        return digitPart + letterPart;

    }

}
