package com.example.ctdbackend.dto;

import com.example.ctdbackend.entity.BookedTripDetails;
import com.example.ctdbackend.entity.Employee;
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
public class SavedBookedDetailsDto {

    private BookedTripDetails bookedTripDetails;
    String employeeName;
    String source_location;
    String dest_location;
    Date trip_start_date;
    Date trip_end_date;
//    private RequestedTrip requestedTrip;
}
