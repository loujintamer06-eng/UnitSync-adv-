package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends JpaRepository<AmenityModel, Integer> {
    List<AmenityModel> findByAvailability(String availability);
    List<AmenityModel> findByLocation(String location);
}