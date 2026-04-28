package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityService {

    @Autowired
    AmenityRepository amenityRepository;

    public List<AmenityModel> getAll() {
        return amenityRepository.findAll();
    }

    public List<AmenityModel> getByAvailability(String availability) {
        return amenityRepository.findByAvailability(availability);
    }

    public String addAmenity(AmenityModel amenity) {
        if (amenity.getName() == null || amenity.getName().isEmpty()) {
            return "Amenity name is required";
        }
        amenityRepository.save(amenity);
        return "Amenity added successfully";
    }

    public String updateAvailability(int amenityId, String availability) {
        AmenityModel amenity = amenityRepository.findById(amenityId)
            .orElseThrow(() -> new RuntimeException("Amenity not found"));
        amenity.setAvailability(availability);
        amenityRepository.save(amenity);
        return "Availability updated successfully";
    }

    public String deleteAmenity(int amenityId) {
        amenityRepository.deleteById(amenityId);
        return "Amenity deleted successfully";
    }
}