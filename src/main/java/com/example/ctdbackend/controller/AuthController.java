package com.example.ctdbackend.controller;

import com.example.ctdbackend.dto.LoginDto;
import com.example.ctdbackend.dto.RegisterDto;
import com.example.ctdbackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private AuthService authService;

    // Build Register REST API
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto)
//    {
//        String response = authService.register(registerDto);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

    // Build login API
    @PostMapping("/login")
    public ResponseEntity<String[]> login(@RequestBody LoginDto loginDto)
    {
        System.out.println(loginDto.getEmail());
        System.out.println(loginDto.getPassword());

        String[] arr = authService.login(loginDto);

        String role = arr[0];

        if(role.equals("employee"))
        {
            return ResponseEntity.ok(arr);
        }
        if(role.equals("manager"))
        {
            return ResponseEntity.ok(arr);
        }
        if(role.equals("traveldesk"))
        {
            System.out.println("here");
            return ResponseEntity.ok(arr);
        }
        return ResponseEntity.badRequest().body(arr);
    }
}
