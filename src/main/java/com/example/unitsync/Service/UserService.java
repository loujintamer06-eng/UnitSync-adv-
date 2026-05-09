package com.example.unitsync.Service;

import com.example.unitsync.Model.User;
import com.example.unitsync.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepository;
    
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
