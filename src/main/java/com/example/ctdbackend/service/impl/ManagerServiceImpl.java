package com.example.ctdbackend.service.impl;

import com.example.ctdbackend.dto.DeclinedTripsDto;
import com.example.ctdbackend.dto.ManagerPendingRequestDTO;
import com.example.ctdbackend.entity.*;
import com.example.ctdbackend.repository.ApprovedTripsRepository;
import com.example.ctdbackend.repository.EmployeeRepository;
import com.example.ctdbackend.repository.ManagerDeclinedTripRepository;
import com.example.ctdbackend.repository.RequestedTripRepository;
import com.example.ctdbackend.service.ManagerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

//    @Autowired
    RequestedTripRepository requestedTripRepository;
    EmployeeRepository employeeRepository;

    ApprovedTripsRepository approvedTripsRepository;

    ManagerDeclinedTripRepository  managerDeclinedTripRepository;
    ManagerServiceImpl(ManagerDeclinedTripRepository  managerDeclinedTripRepository, RequestedTripRepository requestedTripRepository, EmployeeRepository employeeRepository, ApprovedTripsRepository approvedTripsRepository)
    {
        this.requestedTripRepository = requestedTripRepository;
        this.employeeRepository = employeeRepository;
        this.managerDeclinedTripRepository = managerDeclinedTripRepository;
        this.approvedTripsRepository = approvedTripsRepository;
    }

    @Override
    public List<RequestedTrip> getRequestedTripManagerDetails(String currentLoggedInId) {
        Long id = Long.parseLong(currentLoggedInId);
        return this.requestedTripRepository.findAllByManager(id);
    }

    @Override
    public List<ManagerPendingRequestDTO> getRequestedTripManagerDetailsWithName(List<RequestedTrip> managerPendingRequests) {
        List<ManagerPendingRequestDTO> result = new ArrayList<>();
        for (RequestedTrip managerPendingRequest : managerPendingRequests) {
            ManagerPendingRequestDTO managerPendingRequestDTO = new ManagerPendingRequestDTO();
            Employee e = null;
            e = employeeRepository.findById(managerPendingRequest.getEmployee().getId()).orElse(null);
            if (e != null) {
                System.out.println(e.getName());
//                requestsWithName.put(e.getName(), managerPendingRequest);
                managerPendingRequestDTO.setEmployeeName(e.getName());
                managerPendingRequestDTO.setRequestedTrip(managerPendingRequest);
                result.add(managerPendingRequestDTO);
            }

        }
        return result;
    }

    @Override
    @Transactional
    public int changeRequestedTripStatusToFalse(String changeStatusOfId) {
        Long id = Long.parseLong(changeStatusOfId);
        return this.approvedTripsRepository.updateStatusToFalse(id);
    }

    @Override
    public List<ManagerPendingRequestDTO> getApprovedTripsWithName(List<ApprovedTrips> managerApprovedRequests)
    {
        List<ManagerPendingRequestDTO> result = new ArrayList<>();
        for (ApprovedTrips managerApprovedRequest : managerApprovedRequests)
        {
            ManagerPendingRequestDTO managerPendingRequestDTO = new ManagerPendingRequestDTO();
            Employee e = null;
            e = employeeRepository.findById(managerApprovedRequest.getEmployee().getId()).orElse(null);

            if (e != null)
            {
                System.out.println(e.getName());
//                requestsWithName.put(e.getName(), managerPendingRequest);
                managerPendingRequestDTO.setEmployeeName(e.getName());
            }

            RequestedTrip requestedTrip = null;
            requestedTrip = requestedTripRepository.findById(managerApprovedRequest.getRequestedTrip().getReq_trip_id()).orElse(null);

            if(requestedTrip != null)
            {
                managerPendingRequestDTO.setRequestedTrip(requestedTrip);
            }

            managerPendingRequestDTO.setApprovedDate(managerApprovedRequest.getApprovedDate());
            result.add(managerPendingRequestDTO);

        }
        return result;
    }

    @Override
    @Transactional
    public List<ApprovedTrips> getApprovedTrips(String currentLoggedInId) {
        Long id = Long.parseLong(currentLoggedInId);
        return this.approvedTripsRepository.findAllApprovedTripsByManager(id);
    }

    @Override
    public int insertIntoManagerDeclinedTable(DeclinedTripsDto declinedTripsDto) {


        RequestedTrip requestedTrip = requestedTripRepository.findById(Long.parseLong(declinedTripsDto.getTripIdToBeDeclined())).orElse(null);

        DeclinedTrips declinedTrips = new DeclinedTrips();

        Manager m = new Manager();
        m.setId(Long.parseLong(declinedTripsDto.getCurrentLoggedInManagerId()));


//        declinedTrips.setRequestedTrip(declinedTripsDto.getRequestedTrip());
        declinedTrips.setDecline_reason(declinedTripsDto.getDecline_reason());
        declinedTrips.setManager(m);
        declinedTrips.setRequestedTrip(requestedTrip);

        this.managerDeclinedTripRepository.save(declinedTrips);

        return 0;
    }

    @Override
    @Transactional
    public int changeRequestedTripStatus(String changeStatusOfId) {
        Long id = Long.parseLong(changeStatusOfId);
        return this.requestedTripRepository.updateStatusToTrue(id);
    }

    @Override
    public int insertIntoApprovedTable(String id) {
        RequestedTrip requestedTrip = this.requestedTripRepository.findById(Long.parseLong(id)).orElse(null);
        ApprovedTrips approvedTrips = new ApprovedTrips();
        if(requestedTrip != null)
        {
            Employee employee = new Employee();
            Manager manager = new Manager();
            employee.setId(requestedTrip.getEmployee().getId());
            manager.setId(requestedTrip.getManager().getId());
            approvedTrips.setRequestedTrip(requestedTrip);
            approvedTrips.setEmployee(employee);
            approvedTrips.setManager(manager);
            approvedTrips.setIsBookedByTravelDesk(false);
            approvedTrips.setApprovedDate(new Date());
            this.approvedTripsRepository.save(approvedTrips);
        }
        return 0;
    }

    @Override
    public Integer[] getCount(String currLoggedInID) {
        Long id = Long.parseLong(currLoggedInID);
        int totalPendingRequest = requestedTripRepository.getCountOfTotalRequestedTripsOfManager(id);
        int totalApprovedTrips = requestedTripRepository.getCountOfTotalApprovedTripsOfManager(id);
        int totalDeclinedTrips = requestedTripRepository.getCountOfTotalDeclinedTripsOfManager(id);
        int totalForwardedTrips = requestedTripRepository.getCountOfTotalForwardedTripsOfManager(id);

        return new Integer[]{totalPendingRequest, totalApprovedTrips, totalDeclinedTrips, totalForwardedTrips};
    }


}
