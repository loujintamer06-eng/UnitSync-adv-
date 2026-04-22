package com.residential.controller;

import com.residential.model.Amenity;
import com.residential.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    // GET /api/amenities → Get all amenities
    @GetMapping
    public ResponseEntity<List<Amenity>> getAllAmenities() {
        return ResponseEntity.ok(amenityService.getAllAmenities());
    }

    // GET /api/amenities/{id} → Get amenity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Amenity> getAmenityById(@PathVariable Long id) {
        return amenityService.getAmenityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/amenities/available → Get all available amenities
    @GetMapping("/available")
    public ResponseEntity<List<Amenity>> getAvailableAmenities() {
        return ResponseEntity.ok(amenityService.getAvailableAmenities());
    }

    // GET /api/amenities/status/{status} → Get amenities by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Amenity>> getAmenitiesByStatus(@PathVariable Amenity.AmenityStatus status) {
        return ResponseEntity.ok(amenityService.getAmenitiesByStatus(status));
    }

    // GET /api/amenities/location/{location} → Get amenities by location
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Amenity>> getAmenitiesByLocation(@PathVariable String location) {
        return ResponseEntity.ok(amenityService.getAmenitiesByLocation(location));
    }

    // GET /api/amenities/search?keyword=pool → Search amenities by name
    @GetMapping("/search")
    public ResponseEntity<List<Amenity>> searchAmenities(@RequestParam String keyword) {
        return ResponseEntity.ok(amenityService.searchAmenities(keyword));
    }

    // POST /api/amenities → Create new amenity
    @PostMapping
    public ResponseEntity<Amenity> createAmenity(@RequestBody Amenity amenity) {
        Amenity created = amenityService.createAmenity(amenity);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/amenities/{id} → Update amenity
    @PutMapping("/{id}")
    public ResponseEntity<Amenity> updateAmenity(@PathVariable Long id, @RequestBody Amenity amenity) {
        try {
            return ResponseEntity.ok(amenityService.updateAmenity(id, amenity));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH /api/amenities/{id}/status → Update amenity status only
    @PatchMapping("/{id}/status")
    public ResponseEntity<Amenity> updateAmenityStatus(
            @PathVariable Long id,
            @RequestParam Amenity.AmenityStatus status) {
        try {
            return ResponseEntity.ok(amenityService.updateAmenityStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/amenities/{id} → Delete amenity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable Long id) {
        try {
            amenityService.deleteAmenity(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
