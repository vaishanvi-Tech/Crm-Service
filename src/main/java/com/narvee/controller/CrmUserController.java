package com.narvee.controller;

import com.narvee.commons.RestAPIResponse;
import com.narvee.entity.User;
import com.narvee.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class CrmUserController {

    private final UserService service;

    public CrmUserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public ResponseEntity<RestAPIResponse> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(new RestAPIResponse("Success", "Users retrieved successfully", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestAPIResponse> getUserById(@PathVariable Long id) {
        User user = service.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(new RestAPIResponse("Success", "User retrieved successfully", user));
        } else {
            return ResponseEntity.status(404).body(new RestAPIResponse("Failed", "User not found"));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RestAPIResponse> createUser(@RequestBody User user) {
        try {
            User savedUser = service.createUser(user);
            return ResponseEntity.ok(new RestAPIResponse("Success", "User created successfully", savedUser));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new RestAPIResponse("Failed", "Error creating user: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestAPIResponse> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = service.updateUser(id, user);
            return ResponseEntity.ok(new RestAPIResponse("Success", "User updated successfully", updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new RestAPIResponse("Failed", "Error updating user: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestAPIResponse> deleteUser(@PathVariable Long id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.ok(new RestAPIResponse("Success", "User deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new RestAPIResponse("Failed", "Error deleting user: " + e.getMessage()));
        }
    }

   
}
