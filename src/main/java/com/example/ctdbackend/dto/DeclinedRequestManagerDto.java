package com.example.ctdbackend.dto;

import com.example.ctdbackend.entity.RequestedTrip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeclinedRequestManagerDto {

//    private Long id;

    private String employeeName;

    private RequestedTrip requestedTrip;

    private String decline_reason;

}