package com.example.ctdbackend.controller;

import com.example.ctdbackend.dto.RequestedTripDto;
import com.example.ctdbackend.dto.UpcomingTripsDto;
import com.example.ctdbackend.entity.BookedTripDetails;
import com.example.ctdbackend.entity.RequestedTrip;
import com.example.ctdbackend.service.impl.EmployeeServiceImpl;
//import com.example.ctdbackend.service.impl.RequestedTripServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/employee")
public class EmployeeController {


    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    EmployeeController (EmployeeServiceImpl employeeServiceImpl){

        this.employeeServiceImpl = employeeServiceImpl;
    }

    @PostMapping("/applyForTrip")
    public RequestedTrip createTrip(@RequestBody RequestedTripDto requestedTripDto)
    {
        System.out.println(requestedTripDto.getDest_location());
        System.out.println(requestedTripDto.getTrip_reason());
        System.out.println(requestedTripDto.getSource_location());
        System.out.println(requestedTripDto.getTrip_end_date());
        System.out.println(requestedTripDto.getTrip_start_date());
        System.out.println(requestedTripDto.getCurrent_loggedin_id());
        return employeeServiceImpl.applyForTrip(requestedTripDto);
    }

    // Get all the requests for an employee
    @PostMapping ("/getRequestedTripDetails")
    public List<RequestedTrip> getRequestedTrip(@RequestBody String currentLoggedInId)
    {
        System.out.println("currentLoggedInId " + currentLoggedInId);
        return employeeServiceImpl.getRequestedTripDetails(currentLoggedInId);
    }

    @PostMapping("/getHomePageCountDetails")
    public Integer[] getHomePageCountDetails(@RequestBody String currLoggedInID)
    {
        System.out.println("Inside employee homepage counter controller " + currLoggedInID);
        return employeeServiceImpl.getCount(currLoggedInID);
    }

    @PostMapping("/getUpcomingTrips")
    public List<UpcomingTripsDto> getUpcomingTrips(@RequestBody String currLoggedInID)
    {
       List<BookedTripDetails>  allBookedTripDetails = employeeServiceImpl.getListOfBookedTrips(currLoggedInID);
       return  employeeServiceImpl.getDetailsOfUpcommingTrips(allBookedTripDetails);
    }

    @PostMapping("/getTravelledTrips")
    public List<UpcomingTripsDto> getTravelledTrips(@RequestBody String currLoggedInID)
    {
        List<BookedTripDetails>  allBookedTripDetails = employeeServiceImpl.getListOfBookedTrips(currLoggedInID);
        return  employeeServiceImpl.getDetailsOfTravelledTrips(allBookedTripDetails);
    }
}
