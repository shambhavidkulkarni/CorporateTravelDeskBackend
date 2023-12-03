package com.example.ctdbackend.service;

import com.example.ctdbackend.dto.ApprovedTripsDto;
import com.example.ctdbackend.dto.BookedTripDetailsDto;
import com.example.ctdbackend.dto.SavedBookedDetailsDto;
import com.example.ctdbackend.entity.ApprovedTrips;
import com.example.ctdbackend.entity.BookedTripDetails;
import com.example.ctdbackend.entity.RequestedTrip;

import java.util.List;

public interface TravelDeskService {

    public List<ApprovedTripsDto> pendingRequestTravelDesk(List<ApprovedTrips> approvedTrips);

    public List<ApprovedTrips> getAllApprovedTrips();

    public Integer[] getCount();

    int saveBookedTripDetails(BookedTripDetailsDto bookedTripDetailsDto);

    public List<BookedTripDetails> getAllBookedTrips();

    public List<SavedBookedDetailsDto> getAllBookedTripsWithEmployee();


}
