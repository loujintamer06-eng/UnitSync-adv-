package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityService {

    
    @Autowired// spring already 3amil object lewa7do fana basta3milo
    AmenityRepository amenityRepository;
    
    public List<AmenityModel> getAll() {// methode ready min spring fil repo
        return amenityRepository.findAll();
    }

    public List<AmenityModel> getByAvailability(String availability) {// ba5od obj min elmodle 
    	//3ashan access el avalability min edb
        return amenityRepository.findByAvailability(availability);
    }
    
    public String updateAvailability(int amenityId, String availability) {
        Optional<AmenityModel> result = amenityRepository.findById(amenityId);
        
        if (result.isEmpty()) {
            return "Amenity not found";
        }
        
        AmenityModel a = result.get();
        a.setAvailability(availability);
        amenityRepository.save(a);
        return "Updated successfully";
    }

    public String addAmenity(AmenityModel amenity) {
        if (amenity.getName() == null || amenity.getName().isEmpty()) {
        	//3ashan aadd el ameninty from the button w validation
            return "Amenity name is required";
        }
        amenityRepository.save(amenity);// bsave el amenity fil db (KELMIT SAVE B TA3MIL INSERT)
        return "Amenity added successfully";
    }

}