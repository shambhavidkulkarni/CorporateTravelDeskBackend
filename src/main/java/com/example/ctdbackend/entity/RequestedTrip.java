package com.example.ctdbackend.entity;

import com.example.ctdbackend.dto.BookedTripDetailsDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "requested_trip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestedTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long req_trip_id;

    private String source_location;

    private  String dest_location;

    private Date req_trip_date;

    private Date trip_start_date;

    private Date trip_end_date;

    private String trip_reason;

//    private Boolean req_trip_status;
    private Long req_trip_status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id_fk", referencedColumnName = "manager_id")
    private Manager manager;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "emp_id_fk",referencedColumnName = "emp_id")
    private Employee employee;

    @JsonManagedReference
    @OneToOne(mappedBy = "requestedTrip")
    private ApprovedTrips approvedTrips;

    @JsonManagedReference
    @OneToOne(mappedBy = "requestedTrip")
    private DeclinedTrips DeclinedTrip;

    @JsonManagedReference
    @OneToOne(mappedBy = "requestedTrip")
    private BookedTripDetails bookedTripDetails;
}
