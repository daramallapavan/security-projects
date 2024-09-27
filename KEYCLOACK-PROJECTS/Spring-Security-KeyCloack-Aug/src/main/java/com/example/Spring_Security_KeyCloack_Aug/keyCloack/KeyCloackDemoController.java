package com.example.Spring_Security_KeyCloack_Aug.keyCloack;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/demo")
public class KeyCloackDemoController {
    @GetMapping
    public String hello(){
        return "Hello from spring boot and keyclaok";
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "authorized hello";
    }








}
