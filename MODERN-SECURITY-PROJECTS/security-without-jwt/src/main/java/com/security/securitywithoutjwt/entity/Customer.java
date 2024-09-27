package com.security.securitywithoutjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int customerId;
    @Column(unique = true,name = "email")
    private String customerEmail;
    @Column(name = "name")
    private String customerName;

    @Column(name = "password")
    private String customerPassword;

    @Column(name = "role")
    private String roles;




}
