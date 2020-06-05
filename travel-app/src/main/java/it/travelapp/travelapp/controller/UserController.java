package it.travelapp.travelapp.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import it.travelapp.travelapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.travelapp.travelapp.exception.ResourceNotFoundException;
import it.travelapp.travelapp.model.Review;
import it.travelapp.travelapp.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    //Get number of users
    @GetMapping("/getNum")
    public long getNumberOfUsers() {
        return userRepository.count();
    }

    // Get All Users
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User by id
    @GetMapping("/id={id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    // Get User by email
    @GetMapping("/email={email}")
    public User getUserByEmail(@PathVariable(value = "email") String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
    }

    // Get User by email
    @GetMapping("/username={username}")
    public User getUserByUsername(@PathVariable(value = "username") String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    // Delete a User by email
    @DeleteMapping("/email={email}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "email") String userEmail) {
        User userOLD = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        userRepository.delete(userOLD);

        return ResponseEntity.ok().build();
    }

    // Delete a User by id
    @DeleteMapping("/id={id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    // Update a User
    @PutMapping("/email={email}")
    public User updateStructure(@PathVariable(value = "email") String userEmail,
                                   @Valid @RequestBody User userDetails) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        if (userDetails.getEmail() != null)
            user.setEmail(userDetails.getEmail());

        if (userDetails.getUsername() != null)
            user.setUsername(userDetails.getUsername());

        if (userDetails.getPassword() != null)
            user.setPassword(userDetails.getPassword());

        if (userDetails.getFirstName() != null)
            user.setFirstName(userDetails.getFirstName());

        if (userDetails.getLastName() != null)
            user.setLastName(userDetails.getLastName());

        if (userDetails.getGender() != null)
            user.setGender(userDetails.getGender());

        if (userDetails.getImage() != null)
            user.setImage(userDetails.getImage());

        User updatedStructure = userRepository.save(user);
        return updatedStructure;
    }

    //---- ENDPOINT FOR FOREIGN KEYS

    // Get Reviews by UserID
    @GetMapping("/id={id}/getReviews")
    public Set<Review> getReviewsByUserId(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return user.getReviews();
    }
}