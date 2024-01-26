package com.qp.groceryappapis.controller;

import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.UserRequest;
import com.qp.groceryappapis.payload.UserResponse;
import com.qp.groceryappapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.addUser(userRequest));
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable("userId") Integer userId) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(userRequest, userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("Record Deleted Successfully", HttpStatus.OK));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Integer userId) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserById(userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllUser());
    }

}
