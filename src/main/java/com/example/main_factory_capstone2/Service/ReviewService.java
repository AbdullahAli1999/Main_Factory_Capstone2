package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.*;
import com.example.main_factory_capstone2.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;

    //GET
    public List<Review> getAllReview(){
        return reviewRepository.findAll();
    }
    //ADD
    public boolean addReview(Review review){
        List<Order> userOrder = orderRepository.findOrderByUser_id(review.getUserId());
        for(Order order : userOrder){
            List<OrderDetails> details = orderDetailsRepository.findByOrder_id(order.getId());
            for (OrderDetails details1 : details){
                if(details1.getProduct_id().equals(review.getProductId())){
                    review.setReviewDate(LocalDate.now());
                    reviewRepository.save(review);
                    return true;
                }
            }
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
    //13. top five review
    public List<Review> getTopReviews() {
        List<Review> allReviews = reviewRepository.findAll();
        List<Review> topReviews = new ArrayList<>();

        // Add only reviews with valid ratings
        for (Review review : allReviews) {
            if (review.getRating() > 0) {
                topReviews.add(review);
            }
        }

        // Sort manually by rating in descending order
        for (int i = 0; i < topReviews.size(); i++) {
            for (int j = i + 1; j < topReviews.size(); j++) {
                if (topReviews.get(j).getRating() > topReviews.get(i).getRating()) {
                    Review temp = topReviews.get(i);
                    topReviews.set(i, topReviews.get(j));
                    topReviews.set(j, temp);
                }
            }
        }

        // Return only top 5
        if (topReviews.size() > 5) {
            return topReviews.subList(0, 5);
        }

        return topReviews;
    }
}
