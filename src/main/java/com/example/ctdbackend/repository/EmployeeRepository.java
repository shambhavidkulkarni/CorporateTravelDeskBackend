package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
    Employee findByEmailAndPassword(String email, String password);



//    @Query(value = "select manager_id_fk from Employee where emp_id = currentLoggedinId", nativeQuery = true)
//    Long findByManagerId(Long currentLoggedinId);

//    @Query(value = "SELECT id.manager_id_fk FROM Employee WHERE id.emp_id = :currentLoggedInId", nativeQuery = true)
//    Long findByManagerId(@Param("currentLoggedInId") Long currentLoggedInId);


//    @Query("SELECT Manager.manager_id_fk FROM Employee")
//    Long findManagerId(Long currentLoggedinId);

}
