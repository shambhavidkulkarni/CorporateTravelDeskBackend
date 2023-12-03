package com.example.ctdbackend.service;

import com.example.ctdbackend.dto.DeclinedTripsDto;
import com.example.ctdbackend.dto.ManagerPendingRequestDTO;
import com.example.ctdbackend.entity.ApprovedTrips;
import com.example.ctdbackend.entity.RequestedTrip;

import java.util.List;

public interface ManagerService {

    public List<RequestedTrip> getRequestedTripManagerDetails(String currentLoggedInId);

    public List<ManagerPendingRequestDTO> getRequestedTripManagerDetailsWithName(List<RequestedTrip> managerPendingRequests);

    public List<ManagerPendingRequestDTO> getApprovedTripsWithName(List<ApprovedTrips> managerApprovedRequests);

    public List<ApprovedTrips> getApprovedTrips(String currentLoggedInId);



    public Integer[] getCount(String currLoggedInID);

    public int changeRequestedTripStatusToFalse(String id);

    int insertIntoManagerDeclinedTable(DeclinedTripsDto declinedTripsDto);

    public int changeRequestedTripStatus(String id);

    public int insertIntoApprovedTable(String id);


}
