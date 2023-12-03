package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.ApprovedTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprovedTripsRepository extends JpaRepository<ApprovedTrips,Long> {

    @Query("SELECT approvedTrips FROM ApprovedTrips approvedTrips where approvedTrips.manager.id = :manager_id_fk ")
    List<ApprovedTrips> findAllApprovedTripsByManager(@Param("manager_id_fk") Long  manager_id_fk);

    // Decline pending req by manager
    @Modifying
    @Query("UPDATE RequestedTrip r SET r.req_trip_status = 2 WHERE r.req_trip_id = :req_trip_id")
    int updateStatusToFalse(@Param("req_trip_id") Long req_trip_id);

    @Query("SELECT approvedTrips FROM ApprovedTrips approvedTrips where approvedTrips.isBookedByTravelDesk = FALSE ")
    List<ApprovedTrips> findByIsBookedByTravelDesk();

    @Modifying
    @Query("UPDATE ApprovedTrips a SET a.isBookedByTravelDesk = true WHERE a.requestedTrip.req_trip_id = :requested_trip_id_fk")
    int changeStatusOfApprovedTrip(@Param("requested_trip_id_fk") Long requested_trip_id_fk);


}
