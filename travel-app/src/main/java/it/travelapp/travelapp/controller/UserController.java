package it.travelapp.travelapp.controller;

import it.travelapp.travelapp.exception.ResourceNotFoundException;
import it.travelapp.travelapp.model.User;
import it.travelapp.travelapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // Get All Users
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User by email
    @GetMapping("/email={email}")
    public User getUserByEmail(@PathVariable(value = "email") String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
    }

    // Create a new User
    @PostMapping("/create")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // Delete a User
    @DeleteMapping("/email={email}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "email") String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    // Update a User
    @PutMapping("/email={email}")
    public User updateStructure(@PathVariable(value = "email") String userEmail,
                                     @Valid @RequestBody User userDetails) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        //user.setRegistrationDate(userDetails.getRegistrationDate());
        user.setRealName(userDetails.getRealName());
        user.setRealSurname(userDetails.getRealSurname());
        user.setViewRealName(userDetails.getViewRealName());
        user.setRole(userDetails.getRole());

        User updatedStructure = userRepository.save(user);
        return updatedStructure;
    }
}