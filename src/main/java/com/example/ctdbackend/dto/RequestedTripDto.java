package com.example.ctdbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestedTripDto
{
    private String source_location;
    private  String dest_location;
    private Date req_trip_date;
    private Date trip_start_date;
    private Date trip_end_date;
    private String trip_reason;
    private Long req_trip_status;
    private Long current_loggedin_id;
}
