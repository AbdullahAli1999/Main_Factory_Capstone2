package com.example.main_factory_capstone2.Controller;

import com.example.main_factory_capstone2.Api.ApiResponse;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllusers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(200).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Added"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = userService.updateUser(id, user);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(userService.deleteUser(id));
    }

}
