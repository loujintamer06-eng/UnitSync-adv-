package com.residential.service;

import com.residential.model.Amenity;
import com.residential.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    // Get all amenities
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    // Get amenity by ID
    public Optional<Amenity> getAmenityById(Long id) {
        return amenityRepository.findById(id);
    }

    // Get amenities by status
    public List<Amenity> getAmenitiesByStatus(Amenity.AmenityStatus status) {
        return amenityRepository.findByStatus(status);
    }

    // Get available amenities
    public List<Amenity> getAvailableAmenities() {
        return amenityRepository.findByStatus(Amenity.AmenityStatus.AVAILABLE);
    }

    // Get amenities by location
    public List<Amenity> getAmenitiesByLocation(String location) {
        return amenityRepository.findByLocation(location);
    }

    // Search amenities by name
    public List<Amenity> searchAmenities(String keyword) {
        return amenityRepository.findByNameContainingIgnoreCase(keyword);
    }

    // Create amenity
    public Amenity createAmenity(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    // Update amenity
    public Amenity updateAmenity(Long id, Amenity updatedAmenity) {
        return amenityRepository.findById(id).map(amenity -> {
            amenity.setName(updatedAmenity.getName());
            amenity.setDescription(updatedAmenity.getDescription());
            amenity.setLocation(updatedAmenity.getLocation());
            amenity.setCapacity(updatedAmenity.getCapacity());
            amenity.setOperatingHours(updatedAmenity.getOperatingHours());
            amenity.setStatus(updatedAmenity.getStatus());
            return amenityRepository.save(amenity);
        }).orElseThrow(() -> new RuntimeException("Amenity not found with id: " + id));
    }

    // Update amenity status
    public Amenity updateAmenityStatus(Long id, Amenity.AmenityStatus status) {
        return amenityRepository.findById(id).map(amenity -> {
            amenity.setStatus(status);
            return amenityRepository.save(amenity);
        }).orElseThrow(() -> new RuntimeException("Amenity not found with id: " + id));
    }

    // Delete amenity
    public void deleteAmenity(Long id) {
        if (!amenityRepository.existsById(id)) {
            throw new RuntimeException("Amenity not found with id: " + id);
        }
        amenityRepository.deleteById(id);
    }
}
