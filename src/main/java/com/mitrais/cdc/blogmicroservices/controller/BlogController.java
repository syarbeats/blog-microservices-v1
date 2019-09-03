package com.mitrais.cdc.blogmicroservices.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class BlogController extends CrossOriginController{


    @RequestMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Test");
    }
}
