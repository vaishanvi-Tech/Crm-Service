package com.narvee.serviceimpl;

import com.narvee.entity.User;
import com.narvee.repository.UserRepository;
import com.narvee.service.UserService;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) {  // must match interface exactly
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setAddress(userDetails.getAddress());
        user.setPhoneNo(userDetails.getPhoneNo());
        user.setUrl(userDetails.getUrl());
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
    @Override
    public List<User> searchUsers(User user) {
        return repository.findAll().stream()
                .filter(u -> user.getName() == null || 
                             u.getName().toLowerCase().contains(user.getName().toLowerCase()))
                .filter(u -> user.getEmail() == null || 
                             u.getEmail().toLowerCase().contains(user.getEmail().toLowerCase()))
                .filter(u -> user.getPhoneNo() == null || 
                             u.getPhoneNo().equals(user.getPhoneNo()))
                .filter(u -> user.getAddress() == null || 
                             u.getAddress().toLowerCase().contains(user.getAddress().toLowerCase()))
                .collect(Collectors.toList());
    }

}
