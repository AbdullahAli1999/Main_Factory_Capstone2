package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

}
