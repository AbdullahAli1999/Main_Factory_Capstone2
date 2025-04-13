package com.example.main_factory_capstone2.Controller;

import com.example.main_factory_capstone2.Api.ApiResponse;
import com.example.main_factory_capstone2.Model.Review;
import com.example.main_factory_capstone2.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllReview(){
        return ResponseEntity.status(200).body(reviewService.getAllReview());
    }

    //ADD
    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody @Valid Review review, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(reviewService.addReview(review)){
            return ResponseEntity.status(200).body(new ApiResponse("Review Added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateReview(@PathVariable Integer id,@RequestBody @Valid Review review,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = reviewService.updateReview(id, review);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Review is updated"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found!"));
    }

    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delReview(@PathVariable Integer id){
        boolean isDeleted = reviewService.deleteReview(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Review Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
}
