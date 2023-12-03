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
public class DeclinedTripsDto {
    private String tripIdToBeDeclined;

    private String currentLoggedInManagerId;

    private String decline_reason;

//    private RequestedTrip requestedTrip;

}
