package com.sontud.jwt.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class EmployeeController {

    @GetMapping(value = "/hello")
    public String getEmployees() {
        return "Hello Word";
    }
}