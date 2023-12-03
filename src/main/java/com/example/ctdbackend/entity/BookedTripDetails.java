package com.example.ctdbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booked_trip_details")
public class BookedTripDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booked_trip_id;

    private String hotelName;
    private String hotelAddress;
    private Long hotelPrice;
    private String hotelCity;
    private String hotelImgUrl;

    private String cabName;
    private String cabNumber;
    private String driverName;
    private String driverNumber;
    private Long cabRent;
    private int total_expenses;

    private Date trip_booked_on;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "requested_trip_id_fk", referencedColumnName = "req_trip_id")
    private RequestedTrip requestedTrip;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "emp_id_fk",referencedColumnName = "emp_id")
    private Employee employee;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id_fk", referencedColumnName = "manager_id")
    private Manager manager;
}
