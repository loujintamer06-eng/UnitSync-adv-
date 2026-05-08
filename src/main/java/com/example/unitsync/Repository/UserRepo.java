package com.example.unitsync.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    // Find by username (String)
    Optional<User> findByUsername(String username);
}