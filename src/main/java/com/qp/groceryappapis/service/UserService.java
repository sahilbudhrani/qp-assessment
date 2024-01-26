package com.qp.groceryappapis.service;

import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.UserResponse;
import com.qp.groceryappapis.payload.UserRequest;

import java.util.List;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userResponse, Integer id) throws ResourceNotFoundException;
    UserResponse getUserById(Integer id) throws ResourceNotFoundException;
    List<UserResponse> getAllUser();
    void deleteUser(Integer id) throws ResourceNotFoundException;
}
