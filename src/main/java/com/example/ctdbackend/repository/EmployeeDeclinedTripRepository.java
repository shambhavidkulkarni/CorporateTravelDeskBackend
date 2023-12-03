package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.DeclinedTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDeclinedTripRepository extends JpaRepository<DeclinedTrips, Long> {



}
