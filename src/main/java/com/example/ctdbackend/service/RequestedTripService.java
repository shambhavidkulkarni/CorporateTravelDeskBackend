package com.example.ctdbackend.service;

import com.example.ctdbackend.dto.*;
import com.example.ctdbackend.entity.ApprovedTrips;
import com.example.ctdbackend.entity.RequestedTrip;

import java.util.List;

public interface RequestedTripService {

    public List<PendingRequestWithDeclinedReason> getRequestedTripDetailsWithReason(String currentLoggedInId);
//    public RequestedTrip applyForTrip(RequestedTripDto requestedTripDto);

    // Get requested trip for a particular employee
//    public List<RequestedTrip> getRequestedTripDetails(String currentLoggedInId);
//    public List<RequestedTrip> getRequestedTripManagerDetails(String currentLoggedInId);

//    public HashMap<String, RequestedTrip> getRequestedTripManagerDetailsWithName(List<RequestedTrip> managerPendingRequests);
//    public List<ManagerPendingRequestDTO> getRequestedTripManagerDetailsWithName(List<RequestedTrip> managerPendingRequests);

//    public List<ManagerApprovedRequestDTO> getApprovedTripsWithName(List<ApprovedTrips> managerApprovedRequests);
//    public List<ManagerPendingRequestDTO> getApprovedTripsWithName(List<ApprovedTrips> managerApprovedRequests);

//    public int changeRequestedTripStatus(String id);

//    public int changeRequestedTripStatusToFalse(String id);

//    public int insertIntoApprovedTable(String id);

//    public DeclinedRequestManagerDto insertIntoDeclinedTable(String id, String reason);

//    int insertIntoManagerDeclinedTable(DeclinedTripsDto declinedTripsDto);

//    public List<ApprovedTrips> getApprovedTrips(String currentLoggedInId);

//    public List<TravelDeskPendingRequest> pendingReqInTravelDesk();

    // Travel desk pending trip Requests
//    public List<ApprovedTripsDto> pendingRequestTravelDesk(List<ApprovedTrips> approvedTrips);

//    public List<ApprovedTrips> getAllApprovedTrips();

}
