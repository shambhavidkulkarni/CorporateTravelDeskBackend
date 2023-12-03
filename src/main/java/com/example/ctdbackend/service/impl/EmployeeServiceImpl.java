package com.example.ctdbackend.service.impl;

import com.example.ctdbackend.dto.BookedTripDetailsDto;
import com.example.ctdbackend.dto.RequestedTripDto;
import com.example.ctdbackend.dto.UpcomingTripsDto;
import com.example.ctdbackend.entity.BookedTripDetails;
import com.example.ctdbackend.entity.Employee;
import com.example.ctdbackend.entity.Manager;
import com.example.ctdbackend.entity.RequestedTrip;
import com.example.ctdbackend.repository.BookedTripDetailsRepository;
import com.example.ctdbackend.repository.DeclinedTripRepository;
import com.example.ctdbackend.repository.EmployeeRepository;
import com.example.ctdbackend.repository.RequestedTripRepository;
import com.example.ctdbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService
{


    @Autowired
    private RequestedTripRepository requestedTripRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeclinedTripRepository declinedTripRepository;
    @Autowired
    private BookedTripDetailsRepository bookedTripDetailsRepository;

    @Override
    public RequestedTrip applyForTrip(RequestedTripDto requestedTripDto) {
        RequestedTrip requestedTrip = new RequestedTrip();

        requestedTrip.setSource_location(requestedTripDto.getSource_location());
        requestedTrip.setDest_location(requestedTripDto.getDest_location());
        requestedTrip.setReq_trip_date(requestedTripDto.getReq_trip_date());
        requestedTrip.setTrip_reason(requestedTripDto.getTrip_reason());
        requestedTrip.setReq_trip_status(requestedTripDto.getReq_trip_status());
        requestedTrip.setTrip_start_date(requestedTripDto.getTrip_start_date());
        requestedTrip.setTrip_end_date(requestedTripDto.getTrip_end_date());

        Long id = requestedTripDto.getCurrent_loggedin_id();

        System.out.println("Current logged in user id : " + id);
        Employee employee = employeeRepository.findById(id).orElse(null);

        Manager manager = new Manager();
        Employee employee1 = new Employee();
        manager.setId(employee.getManager().getId());
        employee1.setId(id);
        requestedTrip.setEmployee(employee1);
        requestedTrip.setManager(manager);
        requestedTrip.setReq_trip_status(0L);
        requestedTrip.setReq_trip_date(new Date());

        requestedTripRepository.save(requestedTrip);

        return null;
    }

    @Override
    public List<RequestedTrip> getRequestedTripDetails(String currentLoggedInId) {
        Long id = Long.parseLong(currentLoggedInId);
        return this.requestedTripRepository.findAllByEmployee(id);
    }

    @Override
    public List<UpcomingTripsDto> getDetailsOfUpcommingTrips(List<BookedTripDetails> bookedTripDetails) {

        List<UpcomingTripsDto> result = new ArrayList<>();
        for(BookedTripDetails bookedTripDetail : bookedTripDetails)
        {
            UpcomingTripsDto upcomingTripsDto = new UpcomingTripsDto();
            RequestedTrip requestedTrip = new RequestedTrip();

            requestedTrip = requestedTripRepository.findById(bookedTripDetail.getRequestedTrip().getReq_trip_id()).orElse(null);
            Date start = requestedTrip.getTrip_start_date();
            Date curr = new Date();

            if(start != null)
            {
                int comp = start.compareTo(curr);
//            date1.compareTo(date2)
                // > 0 means date1 is after date 2
                if(comp > 0)
                {
                    upcomingTripsDto.setRequestedTrip(requestedTrip);
                    upcomingTripsDto.setBookedTripDetails(bookedTripDetail);
                    result.add(upcomingTripsDto);
                }
            }

        }
        return result;
    }

    @Override
    public List<UpcomingTripsDto> getDetailsOfTravelledTrips(List<BookedTripDetails> bookedTripDetails) {

        List<UpcomingTripsDto> result = new ArrayList<>();
        for(BookedTripDetails bookedTripDetail : bookedTripDetails)
        {
            UpcomingTripsDto upcomingTripsDto = new UpcomingTripsDto();
            RequestedTrip requestedTrip = new RequestedTrip();

            requestedTrip = requestedTripRepository.findById(bookedTripDetail.getRequestedTrip().getReq_trip_id()).orElse(null);
            Date start = requestedTrip.getTrip_start_date();
            Date curr = new Date();


            if(start != null)
            {
                int comp = start.compareTo(curr);
//            date1.compareTo(date2)
                // > 0 means date1 is after date 2
                if(comp < 0)
                {
                    upcomingTripsDto.setRequestedTrip(requestedTrip);
                    upcomingTripsDto.setBookedTripDetails(bookedTripDetail);
                    result.add(upcomingTripsDto);
                }
            }

        }
        return result;
    }

    @Override
    public List<BookedTripDetails> getListOfBookedTrips(String currentLoggedInID) {

        return bookedTripDetailsRepository.findAllByEmpIdFk(Long.parseLong(currentLoggedInID));
//
    }

    public Integer[] getCount(String currLoggedInID)
    {
        Long id = Long.parseLong(currLoggedInID);
        List<BookedTripDetails> bookedTripDetails = getListOfBookedTrips(currLoggedInID);
        System.out.println(bookedTripDetails.toString());
        int totalRequestedTrips = requestedTripRepository.getCountOfTotalRequestedTrips(id);
        int totalApprovedTrips = requestedTripRepository.getCountOfTotalApprovedTrips(id);
        int totalDeclinedTrips = requestedTripRepository.getCountOfTotalDeclinedTrips(id);
        int totalUpcomingTrips = getDetailsOfUpcommingTrips(bookedTripDetails).size();
        int totalTravelledTrips = getDetailsOfTravelledTrips(bookedTripDetails).size();

        System.out.println(totalUpcomingTrips);
        return new Integer[]{totalApprovedTrips, totalRequestedTrips, totalDeclinedTrips,totalUpcomingTrips, totalTravelledTrips};
    }
}
