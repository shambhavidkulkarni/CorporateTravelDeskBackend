package com.example.ctdbackend.service.impl;

import com.example.ctdbackend.dto.LoginDto;
import com.example.ctdbackend.dto.RegisterDto;
import com.example.ctdbackend.entity.Employee;
import com.example.ctdbackend.entity.Manager;
import com.example.ctdbackend.entity.TravelDesk;
import com.example.ctdbackend.exception.APIException;
import com.example.ctdbackend.repository.EmployeeRepository;
import com.example.ctdbackend.repository.ManagerRepository;
import com.example.ctdbackend.repository.TravelDeskRepository;
import com.example.ctdbackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AuthServiceImpl implements AuthService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TravelDeskRepository travelDeskRepository;
//    @Override
//    public String register(RegisterDto registerDto) {
//
//        // check email already exists
//        if(userRepository.existsByEmail(registerDto.getEmail())){
//            throw new APIException(HttpStatus.BAD_REQUEST,"Email already exists");
//        }
//
//        User user = new User();
//        user.setName(registerDto.getName());
//        user.setUsername(registerDto.getUsername());
//        user.setEmail(registerDto.getEmail());
//        user.setPassword(registerDto.getPassword());
//
//        userRepository.save(user);
//
//        return "User registered successfully";
//    }
    @Override
    public String[] login(LoginDto loginDto) {
        Employee employee = employeeRepository.findByEmail(loginDto.getEmail());

        if (employee != null) {
            if (employee.getPassword().equals(loginDto.getPassword())) {
                 Long empId = employeeRepository.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword()).getId();
                 return new String[]{"employee", empId.toString()};
            }
            else {
                System.out.println("password wrong");
            }
        }

        Manager manager = managerRepository.findByEmail(loginDto.getEmail());

        if(manager != null) {
            if (manager.getPassword().equals(loginDto.getPassword())) {
                Long managerId = managerRepository.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword()).getId();
                return new String[]{"manager", managerId.toString()};
            }
            else {
                System.out.println("password wrong");
            }
        }

        TravelDesk travelDesk = travelDeskRepository.findByEmail(loginDto.getEmail());
        if(travelDesk != null) {
            if (travelDesk.getPassword().equals(loginDto.getPassword())) {
                return new String[]{"traveldesk", "1"};
            }
            else {
                System.out.println("password wrong");
            }
        }

        return new String[]{"NOT-FOUND","NOT-FOUND"};
    }
}
