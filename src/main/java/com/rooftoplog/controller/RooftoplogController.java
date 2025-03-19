package com.rooftoplog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RooftoplogController {

    @GetMapping
    public String home() {
        return "Welcome to Rooftoplog!";
    }
}
