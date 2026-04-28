package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AmenityController {

    @Autowired
    AmenityService amenityService;

    @GetMapping("/amenities/all")
    @ResponseBody
    public List<AmenityModel> getAll() {
        return amenityService.getAll();
    }

    @PostMapping("/amenities/add")
    @ResponseBody
    public String addAmenity(@RequestBody AmenityModel amenity) {
        return amenityService.addAmenity(amenity);
    }

    @DeleteMapping("/amenities/delete/{amenityId}")
    @ResponseBody
    public String deleteAmenity(@PathVariable int amenityId) {
        return amenityService.deleteAmenity(amenityId);
    }
    @Autowired
    AmenityRepository amenityRepository;
    
    @PutMapping("/amenities/update/{id}/{availability}")
    @ResponseBody
    public String updateAmenityAvailability(@PathVariable int id, @PathVariable String availability) {
        AmenityModel a = amenityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Amenity not found"));
        a.setAvailability(availability);
        amenityRepository.save(a);
        return "Updated";
    }
 
}