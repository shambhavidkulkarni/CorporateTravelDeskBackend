package com.example.ctdbackend.controller;

import com.example.ctdbackend.dto.ApprovedTripsDto;
import com.example.ctdbackend.dto.BookedTripDetailsDto;
import com.example.ctdbackend.dto.SavedBookedDetailsDto;
import com.example.ctdbackend.entity.ApprovedTrips;
import com.example.ctdbackend.entity.BookedTripDetails;
import com.example.ctdbackend.service.impl.TravelDeskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/travelDesk")
public class TravelDeskController {


    @Autowired
   private TravelDeskServiceImpl travelDeskServiceImpl;

    @GetMapping("/pending-requests")
    public List<ApprovedTripsDto> getPendingRequests() {
        List<ApprovedTrips> approvedTrips = travelDeskServiceImpl.getAllApprovedTrips();
        return travelDeskServiceImpl.pendingRequestTravelDesk(approvedTrips);
    }

    @GetMapping("/getAllApprovedTrips")
    public List<ApprovedTrips> getAllApprovedTrips(){
        return travelDeskServiceImpl.getAllApprovedTrips();
    }

    @GetMapping("/getHomePageCountDetails")
    public Integer[] getHomePageCountDetails()
    {
        return travelDeskServiceImpl.getCount();
    }

    @PostMapping("/saveBookedDetails")
    public int saveBookedDetails(@RequestBody BookedTripDetailsDto bookedTripDetailsDto)
    {
        System.out.println(bookedTripDetailsDto.toString());
        return travelDeskServiceImpl.saveBookedTripDetails(bookedTripDetailsDto);
//        return 0;
    }

    @GetMapping("/getAllBookedTrips")
    public List<BookedTripDetails> getAllBookedTrips()
    {
        return travelDeskServiceImpl.getAllBookedTrips();
    }

    @GetMapping("/getAllBookedTripsWithNameAndTripDetails")
    public List<SavedBookedDetailsDto> getAllBookedTripsWithEmployee()
    {
        return travelDeskServiceImpl.getAllBookedTripsWithEmployee();
    }



//    @PostMapping("/getHomePageCountDetails")
//    public Integer[] getHomePageCountDetails()
//    {
//        System.out.println("Inside Manager homepage counter controller " + currentLoggedInID);
////        return managerServiceImpl.getCount(currentLoggedInID);
//        return null;
//    }

}
