package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller// handels http requests
public class AmenityController {

    @Autowired
    AmenityService amenityService;

    @GetMapping("/amenities/all")//esm el path the el get mapping bcatch el get request
    @ResponseBody//formats the javaobject l json then sends it to frontend 
    public List<AmenityModel> getAll() {//step 2 ba3d ma yinady el methode w 
    	//ya5od el data min el db w yi7otaha f list
        return amenityService.getAll();
    }
    
    @PutMapping("/amenities/update/{id}/{availability}")
    @ResponseBody
    public String updateAmenityAvailability(@PathVariable int id, @PathVariable String availability) {
        return amenityService.updateAvailability(id, availability);
    }

    @PostMapping("/amenities/add")
    @ResponseBody
    public String addAmenity(@RequestBody AmenityModel amenity) {
        return amenityService.addAmenity(amenity);
    }
 
}