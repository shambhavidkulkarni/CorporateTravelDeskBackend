package com.example.ctdbackend.dto;

import com.example.ctdbackend.entity.ApprovedTrips;
import com.example.ctdbackend.entity.RequestedTrip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerApprovedRequestDTO {
    String employeeName;
    ApprovedTrips approvedTrips;
}
