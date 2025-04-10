package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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

}
