package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.DeclinedTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeclinedTripRepository extends JpaRepository<DeclinedTrips,Long> {
    @Query("SELECT d.decline_reason FROM DeclinedTrips d WHERE d.requestedTrip.req_trip_id = :requested_trip_id_fk")
    String getDeclinedReason(@Param("requested_trip_id_fk") Long requested_trip_id_fk);

    @Query("SELECT d FROM DeclinedTrips d WHERE d.manager.id = :manager_id_fk")
    List<DeclinedTrips> findByManagerIdFk(@Param("manager_id_fk") Long manager_id_fk);


//    @Query("SELECT COUNT(d) FROM DeclinedTrips d WHERE d.employee.id = :currLoggedInID")
//    int getCountOfTotalDeclinedTripsOfEmployee(@Param("currLoggedInID") Long currLoggedInID);
}
