package com.example.ctdbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "manager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;
    private String name;
    private String email;
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "manager")
    private List<Employee> employee;

    @JsonManagedReference
    @OneToMany(mappedBy = "manager")
    private List<RequestedTrip> requestedTrip;

    @JsonManagedReference
    @OneToMany(mappedBy = "manager")
    private List<ApprovedTrips> approvedTrips;

    @JsonManagedReference
    @OneToMany(mappedBy = "manager")
    private List<DeclinedTrips> declinedTrips;

    @JsonManagedReference
    @OneToMany(mappedBy = "manager")
    private List<BookedTripDetails> bookedTripDetails;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "manager")
//    private DeclinedTrips declinedTrips;
}
