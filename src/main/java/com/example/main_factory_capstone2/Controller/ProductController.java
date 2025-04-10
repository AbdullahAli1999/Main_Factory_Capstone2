package com.example.main_factory_capstone2.Controller;

import com.example.main_factory_capstone2.Api.ApiResponse;
import com.example.main_factory_capstone2.Model.Order;
import com.example.main_factory_capstone2.Model.Product;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       if( productService.addProduct(product)) {
           return ResponseEntity.status(200).body(new ApiResponse("Product Added"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = productService.updateProduct(id, product);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(productService.deleteProduct(id));
    }
}
