package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.profilemodel;
import com.example.demo.Profilerepo;

@Service
public class profileservice {
	  @Autowired
	    Profilerepo profilerepository;

	  public profilemodel getByUsername(String username) {
	        profilemodel resident = profilerepository.findByUsername(username);
	        if (resident == null) {
	            throw new RuntimeException("Resident not found: " + username);
	        }
	        return resident;
	    }

	   
	    public profilemodel getById(int residentId) {
	        return profilerepository.findById(residentId)
	            .orElseThrow(() -> new RuntimeException("Resident not found: " + residentId));
	    }
	    
	    public void save(profilemodel resident) {
	        profilerepository.save(resident);
	    }
	    
	    public boolean existsByUsername(String username) {
	        return profilerepository.existsByUsername(username);
	    }
	}
