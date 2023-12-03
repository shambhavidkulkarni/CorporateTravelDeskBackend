package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.Employee;
import com.example.ctdbackend.entity.RequestedTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestedTripRepository extends JpaRepository<RequestedTrip,Long> {
//    @Query("select r from RequestedTrip r where r.employee.id = 3")
    @Query("select r from RequestedTrip r where r.employee.id = :id")
    List<RequestedTrip> findAllByEmployee(@Param("id") Long id);


    // Find pending request for manager
    @Query("select r from RequestedTrip r where r.manager.id = :id and r.req_trip_status = 0")
    List<RequestedTrip> findAllByManager(Long id);


    @Modifying
    @Query("UPDATE RequestedTrip r SET r.req_trip_status = 1 WHERE r.req_trip_id = :req_trip_id")
    int updateStatusToTrue(@Param("req_trip_id") Long req_trip_id);


    @Query("SELECT COUNT(r) FROM RequestedTrip r WHERE r.employee.id = :currLoggedInID")
    int getCountOfTotalRequestedTrips(@Param("currLoggedInID") Long currLoggedInID);

    @Query("SELECT COUNT(r) FROM RequestedTrip r WHERE r.employee.id = :currLoggedInID AND r.req_trip_status = 1")
    int getCountOfTotalApprovedTrips(@Param("currLoggedInID") Long currLoggedInID);

    @Query("SELECT COUNT(r) FROM RequestedTrip r WHERE r.employee.id = :currLoggedInID AND r.req_trip_status = 2")
    int getCountOfTotalDeclinedTrips(@Param("currLoggedInID") Long currLoggedInID);

    @Query("SELECT COUNT(r) FROM RequestedTrip r WHERE r.manager.id = :currLoggedInID AND r.req_trip_status = 0")
    int getCountOfTotalRequestedTripsOfManager(@Param("currLoggedInID") Long currLoggedInID);

    @Query("SELECT COUNT(r) FROM RequestedTrip r WHERE r.manager.id = :currLoggedInID AND r.req_trip_status = 1")
    int getCountOfTotalApprovedTripsOfManager(@Param("currLoggedInID") Long currLoggedInID);

    @Query("SELECT COUNT(r) FROM RequestedTrip r WHERE r.manager.id = :currLoggedInID AND r.req_trip_status = 2")
    int getCountOfTotalDeclinedTripsOfManager(@Param("currLoggedInID") Long currLoggedInID);


    @Query("SELECT COUNT(r) FROM RequestedTrip r WHERE r.manager.id = :currLoggedInID AND r.req_trip_status = 1")
    int getCountOfTotalForwardedTripsOfManager(@Param("currLoggedInID") Long currLoggedInID);





}
