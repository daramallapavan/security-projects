package com.example.Spring_Security_KeyCloack_Aug.keyCloack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/response")
public class ResponseController {
    @GetMapping
    public String helloData(){
        return "Hello from spring boot and response";
    }

}
