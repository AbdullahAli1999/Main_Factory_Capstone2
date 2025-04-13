package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.Product;
import com.example.main_factory_capstone2.Model.Review;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Repository.ProductRepository;
import com.example.main_factory_capstone2.Repository.ReviewRepository;
import com.example.main_factory_capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    //GET
    public List<Review> getAllReview(){
        return reviewRepository.findAll();
    }
    //ADD
    public boolean addReview(Review review){
        User users = userRepository.findUserById(review.getUserId());
        Product product = productRepository.findProductById(review.getProductId());
        if(users.getId().equals(review.getUserId()) && product.getId().equals(review.getProductId())){
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    //UPDATE
    public boolean updateReview(Integer id,Review review){
        Review oldReview = reviewRepository.findReviewById(id);
        if(oldReview == null){
            return false;
        }
        oldReview.setRating(review.getRating());
        oldReview.setComment(review.getComment());
        oldReview.setReviewDate(LocalDate.now());
        reviewRepository.save(oldReview);
        return true;
    }

    //DELETE
    public boolean deleteReview(Integer id){
        Review delReview = reviewRepository.findReviewById(id);
        if(delReview == null){
            return false;
        }
        reviewRepository.delete(delReview);
        return true;
    }
}
