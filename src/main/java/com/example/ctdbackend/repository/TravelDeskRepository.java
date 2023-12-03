package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.TravelDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TravelDeskRepository extends JpaRepository<TravelDesk, Long> {

    public TravelDesk findByEmail(String email);
    @Query("SELECT COUNT(approvedTrip) FROM ApprovedTrips approvedTrip WHERE approvedTrip.isBookedByTravelDesk = FALSE")
    public int getCountOfTotalRequestedTripsOfTravelDesk();

    @Query("SELECT COUNT(b) FROM BookedTripDetails b")
    public int getCountOfTotalBookedTripsOfTravelDesk();

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN SUM(b.total_expenses) ELSE 0 END FROM BookedTripDetails b")
    public int getTotalTotalExpensesOfTravelDesk();

//    @Query("SELECT SUM(b.total_expenses) FROM BookedTripDetails b")
//    public int getTotalTotalExpensesOfTravelDesk();
}
