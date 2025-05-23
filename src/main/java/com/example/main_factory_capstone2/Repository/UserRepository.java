package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
    @Query("select u from User u where u.couponCode = ?1")
    User takeCoupon(String coupon);

}
