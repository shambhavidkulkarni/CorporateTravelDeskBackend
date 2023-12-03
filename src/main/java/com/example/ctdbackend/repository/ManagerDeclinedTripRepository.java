package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.DeclinedTrips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerDeclinedTripRepository extends JpaRepository<DeclinedTrips,Long> {
}
