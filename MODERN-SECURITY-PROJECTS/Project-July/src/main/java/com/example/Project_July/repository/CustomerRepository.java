package com.example.Project_July.repository;

import com.example.Project_July.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    Optional<Customer> findByEmail(String email);


    @Query(name = "Select c From Customer c Where c.email:email and password:password ",nativeQuery = true)
    Optional<Customer> findByEmailAndPassword(@Param( "email" ) String email,
                                              @Param("password") String password
                                              );
}
