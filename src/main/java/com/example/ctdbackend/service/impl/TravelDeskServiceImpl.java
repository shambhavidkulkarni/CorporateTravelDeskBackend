package com.example.ctdbackend.service.impl;

import com.example.ctdbackend.dto.ApprovedTripsDto;
import com.example.ctdbackend.dto.BookedTripDetailsDto;
import com.example.ctdbackend.dto.SavedBookedDetailsDto;
import com.example.ctdbackend.entity.*;
import com.example.ctdbackend.repository.*;
import com.example.ctdbackend.service.TravelDeskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TravelDeskServiceImpl implements TravelDeskService {

    @Autowired
    ApprovedTripsRepository approvedTripsRepository;

    @Autowired
    RequestedTripRepository requestedTripRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    TravelDeskRepository travelDeskRepository;

    @Autowired
    BookedTripDetailsRepository bookedTripDetailsRepository;

//    @Autowired
//    MailService mailService;

    @Override
    public List<ApprovedTrips> getAllApprovedTrips() {
        return approvedTripsRepository.findByIsBookedByTravelDesk();
    }

    @Override
    public List<ApprovedTripsDto> pendingRequestTravelDesk(List<ApprovedTrips> approvedTrips) {
        List<ApprovedTripsDto> ans = new ArrayList<>();
        for(ApprovedTrips approvedTrips1 : approvedTrips)
        {
            ApprovedTripsDto approvedTripsDto = new ApprovedTripsDto();
            RequestedTrip requestedTrip = null;
            requestedTrip = requestedTripRepository.findById(approvedTrips1.getRequestedTrip().getReq_trip_id()).orElse(null);
            if(requestedTrip != null)
            {
                approvedTripsDto.setRequestedTrip(requestedTrip);
            }

            Employee e = null;
            e = employeeRepository.findById(approvedTripsDto.getRequestedTrip().getEmployee().getId()).orElse(null);
            if (e != null)
            {
                System.out.println(e.getName());
//                requestsWithName.put(e.getName(), managerPendingRequest);

//                approvedTripsDto.setEmployee(approvedTripsDto.getEmployee());
//                e.setName(approvedTripsDto.getEmployee().getName());
//                approvedTripsDto.setEmployeeName(e.getName());
                approvedTripsDto.setEmployee(e);
            }

            Manager m = null;
            m = managerRepository.findById(approvedTripsDto.getRequestedTrip().getManager().getId()).orElse(null);
            if( m != null)
            {
                System.out.println(m.getName());
//                approvedTripsDto.setManagerName(m.getName());
                approvedTripsDto.setManager(m);
            }

            ans.add(approvedTripsDto);
        }
        return ans;
    }

    public Integer[] getCount()
    {
        int pendingRequest = travelDeskRepository.getCountOfTotalRequestedTripsOfTravelDesk();
        int bookedTrips = travelDeskRepository.getCountOfTotalBookedTripsOfTravelDesk();
        int totalExpense = travelDeskRepository.getTotalTotalExpensesOfTravelDesk();
        return new Integer[]{pendingRequest, bookedTrips, totalExpense};
    }

    @Override
    @Transactional
    public int saveBookedTripDetails(BookedTripDetailsDto bookedTripDetailsDto) {

        BookedTripDetails bookedTripDetails = new BookedTripDetails();
        bookedTripDetails.setHotelName(bookedTripDetailsDto.getHotelName());
        String hotelPrice  = bookedTripDetailsDto.getHotelPrice();
        int index = hotelPrice.indexOf(".");
        int intValue = 0;
        if(index == -1)
        {
            intValue = Integer.parseInt(bookedTripDetailsDto.getHotelPrice());
            bookedTripDetails.setHotelPrice(Long.parseLong(bookedTripDetailsDto.getHotelPrice()));
        }
        else {
            intValue = Integer.parseInt(hotelPrice.substring(0, hotelPrice.indexOf(".")));
            Long longValue = Long.parseLong(hotelPrice.substring(0, hotelPrice.indexOf(".")));
            System.out.println("Integer value " + intValue);
            System.out.println("longValue " + longValue);
            bookedTripDetails.setHotelPrice(longValue);
        }
        bookedTripDetails.setHotelAddress(bookedTripDetailsDto.getHotelAddress());
        bookedTripDetails.setHotelImgUrl(bookedTripDetailsDto.getHotelImgUrl());
        bookedTripDetails.setHotelCity(bookedTripDetailsDto.getHotelCity());

        bookedTripDetails.setCabName(bookedTripDetailsDto.getCabName());
        bookedTripDetails.setCabRent(Long.parseLong(bookedTripDetailsDto.getCabRent()));
        bookedTripDetails.setCabNumber(bookedTripDetailsDto.getCabNumber());
        bookedTripDetails.setDriverName(bookedTripDetailsDto.getDriverName());
        bookedTripDetails.setDriverNumber(bookedTripDetailsDto.getDriverNumber());
        bookedTripDetails.setTotal_expenses(Integer.parseInt(bookedTripDetailsDto.getCabRent()) + intValue);

        bookedTripDetails.setTrip_booked_on(new Date());
        Manager m = new Manager();
        m.setId(Long.parseLong(bookedTripDetailsDto.getManager_id()));
        bookedTripDetails.setManager(m);

        Employee e = new Employee();
        e.setId(Long.parseLong(bookedTripDetailsDto.getEmp_id()));
        bookedTripDetails.setEmployee(e);

        RequestedTrip r = new RequestedTrip();
        r.setReq_trip_id(Long.parseLong(bookedTripDetailsDto.getReq_trip_id()));
        bookedTripDetails.setRequestedTrip(r);

        this.bookedTripDetailsRepository.save(bookedTripDetails);

        int response = approvedTripsRepository.changeStatusOfApprovedTrip(Long.parseLong(bookedTripDetailsDto.getReq_trip_id()));

//        mailService.getBodyForEmail(bookedTripDetailsDto);
        return 0;
    }

    @Override
    public List<BookedTripDetails> getAllBookedTrips() {
        return bookedTripDetailsRepository.findAll();
    }


    @Override
    public List<SavedBookedDetailsDto> getAllBookedTripsWithEmployee() {
        List<BookedTripDetails> d = bookedTripDetailsRepository.findAll();

        List<SavedBookedDetailsDto> ans = new ArrayList<>();
        for(BookedTripDetails b : d)
        {
            SavedBookedDetailsDto bookedTripDetailsDto = new SavedBookedDetailsDto();
//            bookedTripDetailsDto.setEmployee(b.getEmployee());
//            bookedTripDetailsDto.setRequestedTrip(b.getRequestedTrip());
            bookedTripDetailsDto.setEmployeeName(b.getEmployee().getName());
            bookedTripDetailsDto.setSource_location(b.getRequestedTrip().getSource_location());
            bookedTripDetailsDto.setDest_location(b.getRequestedTrip().getDest_location());
            bookedTripDetailsDto.setBookedTripDetails(b);
            bookedTripDetailsDto.setTrip_start_date(b.getRequestedTrip().getTrip_start_date());
            bookedTripDetailsDto.setTrip_end_date(b.getRequestedTrip().getTrip_end_date());
            ans.add(bookedTripDetailsDto);
        }
        return ans;
    }
}
