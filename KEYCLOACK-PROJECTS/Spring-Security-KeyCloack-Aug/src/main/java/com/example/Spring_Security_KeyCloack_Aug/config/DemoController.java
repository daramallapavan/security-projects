/*
package com.example.Spring_Security_KeyCloack_Aug;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    @PreAuthorize("client_role")
    public String hello(){
        return "Hello from spring boot and keyclaok";
    }
    @PreAuthorize("admin_role")

    @GetMapping("/hello2")
    public String hello2(){
        return "authorized hello";
    }

}*/
