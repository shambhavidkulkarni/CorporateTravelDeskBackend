package com.example.ctdbackend.dto;

import com.example.ctdbackend.entity.BookedTripDetails;
import com.example.ctdbackend.entity.RequestedTrip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingTripsDto {
    private BookedTripDetails bookedTripDetails;
    private RequestedTrip requestedTrip;
}
