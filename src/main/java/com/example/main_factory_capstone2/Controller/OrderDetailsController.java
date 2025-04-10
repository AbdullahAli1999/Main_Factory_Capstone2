package com.example.main_factory_capstone2.Controller;

import com.example.main_factory_capstone2.Api.ApiResponse;
import com.example.main_factory_capstone2.Model.Order;
import com.example.main_factory_capstone2.Model.OrderDetails;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Service.OrderDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/details")
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllOrderDetaols(){
        return ResponseEntity.status(200).body(orderDetailsService.getAllOrderDetails());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addOrderDetails(@RequestBody @Valid OrderDetails orderDetails, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(orderDetailsService.addOrderDetails(orderDetails)) {
            return ResponseEntity.status(200).body(new ApiResponse("Order Details Added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrderDetails(@PathVariable Integer id, @RequestBody @Valid OrderDetails orderDetails, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = orderDetailsService.updateOrderDetails(id, orderDetails);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Order Details updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(orderDetailsService.deleteOrderDetails(id));
    }
}
