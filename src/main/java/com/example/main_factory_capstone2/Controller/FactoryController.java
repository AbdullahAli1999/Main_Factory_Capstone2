package com.example.main_factory_capstone2.Controller;

import com.example.main_factory_capstone2.Api.ApiResponse;
import com.example.main_factory_capstone2.Model.Factory;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Service.FactoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/factory")
@RequiredArgsConstructor
public class FactoryController {
    private final FactoryService factoryService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllFactory(){
        return ResponseEntity.status(200).body(factoryService.getAllFactory());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addFactory(@RequestBody @Valid Factory factory, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(200).body(message);
        }
        factoryService.addFactory(factory);
        return ResponseEntity.status(200).body(new ApiResponse("Factory Added"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateFactory(@PathVariable Integer id, @RequestBody @Valid Factory factory,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = factoryService.updateFactory(id, factory);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Factory updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(factoryService.deleteFactory(id));
    }
}
