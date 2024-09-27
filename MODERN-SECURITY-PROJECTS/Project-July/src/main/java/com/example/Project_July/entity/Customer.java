package com.example.Project_July.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerId;


    private String name;

    @Column(unique = true)
    private String email;

    private String role;
    private String password;

    private String phoneNumber;

    private String gender;




}
