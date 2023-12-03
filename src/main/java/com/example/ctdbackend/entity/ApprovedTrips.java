package com.example.ctdbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "approvedTrips")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedTrips
{
    @Column(name = "approved_trip_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approved_on")
    private Date approvedDate;

    private Boolean isBookedByTravelDesk;


    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "requested_trip_id_fk", referencedColumnName = "req_trip_id")
    private RequestedTrip requestedTrip;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id_fk", referencedColumnName = "manager_id")
    private Manager manager;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "emp_id_fk", referencedColumnName = "emp_id")
    private Employee employee;

}
