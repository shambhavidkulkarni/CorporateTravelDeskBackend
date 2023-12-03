package com.example.ctdbackend.repository;

import com.example.ctdbackend.entity.BookedTripDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookedTripDetailsRepository extends JpaRepository<BookedTripDetails, Long> {

    @Query("SELECT b FROM BookedTripDetails b WHERE b.employee.id = :emp_id_fk")
    List<BookedTripDetails> findAllByEmpIdFk(@Param("emp_id_fk") Long emp_id_fk);


    @Query("SELECT b FROM BookedTripDetails b where b.employee.id = 1")
    List<BookedTripDetails> findAllByHotelName();
}
