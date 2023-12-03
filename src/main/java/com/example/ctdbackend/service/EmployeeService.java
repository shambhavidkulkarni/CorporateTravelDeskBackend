package com.example.ctdbackend.service;

import com.example.ctdbackend.dto.BookedTripDetailsDto;
import com.example.ctdbackend.dto.RequestedTripDto;
import com.example.ctdbackend.dto.UpcomingTripsDto;
import com.example.ctdbackend.entity.BookedTripDetails;
import com.example.ctdbackend.entity.RequestedTrip;

import java.util.List;

public interface EmployeeService {

    public Integer[] getCount(String currLoggedInID);

    public RequestedTrip applyForTrip(RequestedTripDto requestedTripDto);


    public List<RequestedTrip> getRequestedTripDetails(String currentLoggedInId);


    public List<UpcomingTripsDto> getDetailsOfUpcommingTrips(List<BookedTripDetails> bookedTripDetails);

    public List<UpcomingTripsDto> getDetailsOfTravelledTrips(List<BookedTripDetails> bookedTripDetails);

    public List<BookedTripDetails> getListOfBookedTrips(String currentLoggedInID);

}
