package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findReviewById(Integer id);
    List<Review> findReviewByUserId(Integer id);


}
