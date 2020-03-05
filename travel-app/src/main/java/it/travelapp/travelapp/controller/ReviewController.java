package it.travelapp.travelapp.controller;

import it.travelapp.travelapp.exception.ResourceNotFoundException;
import it.travelapp.travelapp.model.Review;
import it.travelapp.travelapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    // Get All Reviews
    @GetMapping("/getAll")
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get Review by Id
    @GetMapping("/id={id}")
    public Review getReviewById(@PathVariable(value = "id") Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));
    }

    // Create a new Review
    @PostMapping("/create")
    public Review createReview(@Valid @RequestBody Review review) {
        return reviewRepository.save(review);
    }

    // Delete a Review
    @DeleteMapping("/id={id}")
    public ResponseEntity<?> deleteReview(@PathVariable(value = "id") Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));

        reviewRepository.delete(review);

        return ResponseEntity.ok().build();
    }

    // Update a Review
    @PutMapping("/id={id}")
    public Review updateReview(@PathVariable(value = "id") Long reviewId,
                                   @Valid @RequestBody Review reviewDetails) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewId));

        review.setDescription(reviewDetails.getDescription());
        review.setPoints(reviewDetails.getPoints());
        //review.setDate(reviewDetails.getDate());
        review.setUserID(reviewDetails.getUserID());
        review.setStructureID(reviewDetails.getStructureID());

        Review updateReview = reviewRepository.save(review);
        return updateReview;
    }
}
