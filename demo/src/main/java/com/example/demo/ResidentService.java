package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentService {

    @Autowired
    ResidentRepository residentRepository;

    public List<ResidentModel> getAll() {
        return residentRepository.findAll();
    }

    public ResidentModel getByAptNumber(String aptNumber) {
        return residentRepository.findByAptNumber(aptNumber);
    }

    public List<ResidentModel> getByStatus(String status) {
        return residentRepository.findByStatus(status);
    }

    public String addResident(ResidentModel resident) {
        if (resident.getFirstName() == null || resident.getFirstName().isEmpty()) {
            return "First name is required";
        }
        if (resident.getAptNumber() == null || resident.getAptNumber().isEmpty()) {
            return "Apt number is required";
        }
        residentRepository.save(resident);
        return "Resident added successfully";
    }

    public String updateStatus(int resid, String status) {
        ResidentModel resident = residentRepository.findById(resid)
            .orElseThrow(() -> new RuntimeException("Resident not found"));
        resident.setStatus(status);
        residentRepository.save(resident);
        return "Status updated successfully";
    }

    public String deleteResident(int resid) {
        residentRepository.deleteById(resid);
        return "Resident deleted successfully";
    }
}