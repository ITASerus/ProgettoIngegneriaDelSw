package it.travelapp.travelapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import it.travelapp.travelapp.exception.ResourceNotFoundException;
import it.travelapp.travelapp.model.Review;
import it.travelapp.travelapp.model.Structure;
import it.travelapp.travelapp.model.User;
import it.travelapp.travelapp.repository.ReviewRepository;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    //Get number of structures
    @GetMapping("/getNum")
    public long getNumberOfReviews() {
        return reviewRepository.count();
    }


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
    @PreAuthorize("hasRole('USER')")
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

        review.setTitle(reviewDetails.getTitle());
        review.setDescription(reviewDetails.getDescription());
        review.setPoints(reviewDetails.getPoints());

        Review updateReview = reviewRepository.save(review);
        return updateReview;
    }

    //---- ENDPOINT FOR FOREIGN KEYS

    // Get User by ReviewID
    @GetMapping("/id={id}/getUser")
    public User getUserByReviewId(@PathVariable(value = "id") Long reviewID) {
        Review review = reviewRepository.findById(reviewID).orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewID));
        return review.getUser();
    }

    //Get Structure by ReviewID
    @GetMapping("/id={id}/getStructure")
    public Structure getStructure(@PathVariable(value = "id") Long reviewID) {
        Review review = reviewRepository.findById(reviewID).orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewID));
        return review.getStructure();
    }
}
