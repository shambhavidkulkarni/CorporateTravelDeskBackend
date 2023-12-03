package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.Employee;
import com.example.ctdbackend.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);

    Manager findByEmailAndPassword(String email, String password);

}
