package com.example.ctdbackend.dto;

import com.example.ctdbackend.entity.Employee;
import com.example.ctdbackend.entity.Manager;
import com.example.ctdbackend.entity.RequestedTrip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedTripsDto
{
//    private Long id;
//    private Date approvedDate;

    private RequestedTrip requestedTrip;

    private Manager manager;

    private Employee employee;
}
