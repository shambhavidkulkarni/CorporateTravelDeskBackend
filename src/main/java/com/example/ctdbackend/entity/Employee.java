package com.example.ctdbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;
    private String name;
    private String email;
    private String password;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id_fk")
    private Manager manager;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee")
    private List<ApprovedTrips> approvedTrips;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee")
    private List<BookedTripDetails> bookedTripDetails;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "employee")
//    private List<DeclinedTrips> declinedTrips;
}
