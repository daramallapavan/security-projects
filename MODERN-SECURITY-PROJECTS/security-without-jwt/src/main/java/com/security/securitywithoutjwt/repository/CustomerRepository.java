package com.security.securitywithoutjwt.repository;

import com.security.securitywithoutjwt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByCustomerEmail(String username);
}
