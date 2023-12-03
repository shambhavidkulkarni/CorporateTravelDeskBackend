package com.example.ctdbackend.service;

import com.example.ctdbackend.dto.LoginDto;
import com.example.ctdbackend.dto.RegisterDto;
import com.example.ctdbackend.entity.Employee;

public interface AuthService {
//    String register(RegisterDto registerDto);
    String[]  login(LoginDto loginDto);


}
