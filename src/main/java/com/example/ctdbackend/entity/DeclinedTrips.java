package com.example.ctdbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "declined_trips")
public class DeclinedTrips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "requested_trip_id_fk", referencedColumnName = "req_trip_id")
    private RequestedTrip requestedTrip;

//    @ManyToOne
//    @JoinColumn(name = "emp_id_fk")
//    private Employee employee;

    private String decline_reason;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id_fk", referencedColumnName = "manager_id")
    private Manager manager;

//    private Long emp_id;

//    private Manager manager;
}
