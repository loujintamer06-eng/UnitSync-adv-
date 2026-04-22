package com.residential.repository;

import com.residential.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findByStatus(Amenity.AmenityStatus status);

    List<Amenity> findByLocation(String location);

    List<Amenity> findByNameContainingIgnoreCase(String keyword);
}
