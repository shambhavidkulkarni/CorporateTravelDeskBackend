package com.example.ctdbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookedTripDetailsDto {

    private String hotelName;
    private String hotelAddress;
    private String hotelPrice;
    private String hotelCity;
    private String hotelImgUrl;

    private String cabName;
    private String cabNumber;
    private String driverName;
    private String driverNumber;
    private String cabRent;

    private String req_trip_id;
    private String emp_id;
    private String manager_id;

    @Override
    public String toString() {
        return "BookedTripDetailsDto{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", hotelPrice='" + hotelPrice + '\'' +
                ", hotelCity='" + hotelCity + '\'' +
                ", hotelImgUrl='" + hotelImgUrl + '\'' +
                ", cabName='" + cabName + '\'' +
                ", cabNumber='" + cabNumber + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverNumber='" + driverNumber + '\'' +
                ", cabRent='" + cabRent + '\'' +
                ", req_trip_id='" + req_trip_id + '\'' +
                ", emp_id='" + emp_id + '\'' +
                ", manager_id='" + manager_id + '\'' +
                '}';
    }
}
