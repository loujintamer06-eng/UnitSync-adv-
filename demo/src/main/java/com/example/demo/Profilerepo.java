package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.profilemodel;
@Repository
public interface Profilerepo extends JpaRepository<profilemodel, Integer>  {



     // Spring Data بيعمل الـ query تلقائي من اسم الميثود
    // SELECT * FROM residents WHERE username = ?
    profilemodel findByUsername(String username);

    // SELECT * FROM residents WHERE resident_id = ?
    // دي موجودة تلقائي من JpaRepository باسم findById
    

    boolean existsByUsername(String username); 
}