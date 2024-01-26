package com.qp.groceryappapis.service.impl;

import com.qp.groceryappapis.entity.Role;
import com.qp.groceryappapis.entity.User;
import com.qp.groceryappapis.exception.ResourceNotFoundException;
import com.qp.groceryappapis.payload.UserResponse;
import com.qp.groceryappapis.payload.UserRequest;
import com.qp.groceryappapis.repository.RoleRepository;
import com.qp.groceryappapis.repository.UserRepository;
import com.qp.groceryappapis.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        Set<Role> roles = userRequest.getRoles().stream()
                .map(role -> roleRepository.findById(role).orElseThrow(() -> new ResourceNotFoundException("Role", "ID", String.valueOf(role))))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User save = userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, Integer id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", String.valueOf(id)));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Integer id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", String.valueOf(id)));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
        return userResponses;
    }

    @Override
    public void deleteUser(Integer id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", String.valueOf(id)));
        userRepository.delete(user);
    }
}
