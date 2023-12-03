package com.example.ctdbackend.controller;

import com.example.ctdbackend.dto.DeclinedRequestEmployeeDto;
import com.example.ctdbackend.dto.DeclinedTripsDto;
import com.example.ctdbackend.dto.ManagerPendingRequestDTO;
import com.example.ctdbackend.entity.ApprovedTrips;
import com.example.ctdbackend.entity.DeclinedTrips;
import com.example.ctdbackend.entity.RequestedTrip;
import com.example.ctdbackend.repository.DeclinedTripRepository;
import com.example.ctdbackend.repository.RequestedTripRepository;
import com.example.ctdbackend.service.impl.ManagerServiceImpl;
//import com.example.ctdbackend.service.impl.RequestedTripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
//    @Autowired
//    private RequestedTripServiceImpl requestedTripServiceImpl;

    @Autowired
    private ManagerServiceImpl managerServiceImpl;


    @Autowired
    DeclinedTripRepository declinedTripRepository;

    @Autowired
    RequestedTripRepository requestedTripRepository;

    // Get all the details under current logged in manager
    @PostMapping ("/getRequestedTripManagerDetails")
    public List<RequestedTrip> getRequestedTripManagerDetails(@RequestBody String currentLoggedInId)
    {
        System.out.println("currentLoggedInId " + currentLoggedInId);
        return managerServiceImpl.getRequestedTripManagerDetails(currentLoggedInId);
    }


    // Display all the pending requests for a manager
    @PostMapping("/getPendingRequestsManager")
    public List<ManagerPendingRequestDTO> getPendingRequestsManager(@RequestBody String currentLoggedInId)
    {
        System.out.println("currentLoggedInId " + currentLoggedInId);
        List<RequestedTrip> requestedTripRows = managerServiceImpl.getRequestedTripManagerDetails(currentLoggedInId);

        List<ManagerPendingRequestDTO> managerPendingRequestDTO = managerServiceImpl.getRequestedTripManagerDetailsWithName(requestedTripRows);

        System.out.println("inside manager controller ");
        return managerPendingRequestDTO;
    }


    // List of  Declined Trips By Manager
    @PostMapping("/getDeclinedTrips")
    public List<DeclinedRequestEmployeeDto> getDeclinedTrips(@RequestBody String currentLoggedInId)
    {
        List<DeclinedTrips> managerDeclinedTrips = declinedTripRepository.findByManagerIdFk(Long.parseLong(currentLoggedInId));
        List<RequestedTrip> requestedTripRows = new ArrayList<>();
        List<DeclinedRequestEmployeeDto> result = new ArrayList<>();
        for(DeclinedTrips managerDeclinedTrip: managerDeclinedTrips)
        {
            DeclinedRequestEmployeeDto nameWithTrip = new DeclinedRequestEmployeeDto();
            RequestedTrip r = requestedTripRepository.findById(managerDeclinedTrip.getRequestedTrip().getReq_trip_id()).orElse(null);
            if(r != null)
            {
                nameWithTrip.setDecline_reason(managerDeclinedTrip.getDecline_reason());
                nameWithTrip.setRequestedTrip(r);
                nameWithTrip.setEmployeeName(r.getEmployee().getName());
            }
            result.add(nameWithTrip);
        }
        return result;
    }



    // Approve request
    // First change status to 1 and then insert into approved table
    @PostMapping("/approvePendingRequest")
    public int approveRequest(@RequestBody String idToBeApproved)
    {
        System.out.println("idToBeApproved " + idToBeApproved);
        int statusResponse = managerServiceImpl.changeRequestedTripStatus(idToBeApproved);
        System.out.println("statusResponse"+statusResponse);
        int insertResponse = managerServiceImpl.insertIntoApprovedTable(idToBeApproved);
        System.out.println("insertResponse"+insertResponse);
        return statusResponse;
    }



    // Approve request
    // First change status to 2 and then insert into declined table
    @PostMapping("/declinePendingRequest")
    public int declinedRequest(@RequestBody DeclinedTripsDto declinedTripsDto)
    {
        System.out.println("declinePendingRequest ");
        System.out.println(declinedTripsDto.getTripIdToBeDeclined() +" " +declinedTripsDto.getDecline_reason());
        int statusResponse = managerServiceImpl.changeRequestedTripStatusToFalse(declinedTripsDto.getTripIdToBeDeclined());
        System.out.println("statusResponse" + statusResponse);
        int insertResponse = managerServiceImpl.insertIntoManagerDeclinedTable(declinedTripsDto);
        System.out.println("insertResponse"+insertResponse);
        return insertResponse;
    }



    // List of approved trips by manager
    @PostMapping("/getApprovedTrips")
    public List<ManagerPendingRequestDTO> getApprovedTrips(@RequestBody String currentLoggedInID)
    {
        System.out.println("currentLoggedInID " + currentLoggedInID);
        List<ApprovedTrips> requestedTripRows = managerServiceImpl.getApprovedTrips(currentLoggedInID);
        List<ManagerPendingRequestDTO> managerPendingRequestDTO = managerServiceImpl.getApprovedTripsWithName(requestedTripRows);
        System.out.println("inside manager controller ");

        return managerPendingRequestDTO;
    }


    @PostMapping("/getHomePageCountDetails")
    public Integer[] getHomePageCountDetails(@RequestBody String currentLoggedInID)
    {
        System.out.println("Inside Manager homepage counter controller " + currentLoggedInID);
        return managerServiceImpl.getCount(currentLoggedInID);
    }

}
