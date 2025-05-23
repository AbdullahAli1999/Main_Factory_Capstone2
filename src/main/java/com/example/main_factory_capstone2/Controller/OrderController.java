package com.example.main_factory_capstone2.Controller;

import com.example.main_factory_capstone2.Api.ApiResponse;
import com.example.main_factory_capstone2.Model.Order;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Service.OrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    //ADD
    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid Order order, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(orderService.addOrder(order)) {
            return ResponseEntity.status(200).body(new ApiResponse("Order Added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @RequestBody @Valid Order order, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = orderService.updateOrder(id, order);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Order updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(orderService.deleteOrder(id));
    }
    //4.
    @PostMapping("/place/{userId}/{productId}/{coupon}")
    public ResponseEntity placeOrder(@PathVariable Integer userId,@PathVariable Integer productId,@PathVariable String coupon){
        orderService.placeOrder(userId,productId,coupon);
        return ResponseEntity.status(200).body(new ApiResponse("Order placed"));
    }
    //5.
    @PostMapping("/return/{orderId}")
    public ResponseEntity returnOrder(@PathVariable Integer orderId){
        Order order = orderService.returnOrder(orderId);
        if(order== null){
            return ResponseEntity.status(400).body(new ApiResponse("Order not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Order returned"));
    }

    //7.
    @GetMapping("/get-status/{status}")
    public ResponseEntity getStatusOrder(@PathVariable String status){
        return ResponseEntity.status(200).body(orderService.getOrderStatus(status));
    }

    //8.
    @PutMapping("/cancel/{id}")
    public ResponseEntity cancelOrder(@PathVariable Integer id){
        boolean isCancelled = orderService.cancelOrder(id);
        if(isCancelled){
            return ResponseEntity.status(200).body(new ApiResponse("Order cancelled"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Order cannot be cancelled"));
    }

}
