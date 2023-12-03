package com.example.ctdbackend.dto;

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
public class ManagerPendingRequestDTO {
    String employeeName;
    Date approvedDate;
    RequestedTrip requestedTrip;
}
