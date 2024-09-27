package com.example.Kalki_Project.product.controller;

import com.example.Kalki_Project.product.entity.Product;
import com.example.Kalki_Project.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        Product createdProduct=productService.createProduct(product);
        return new ResponseEntity<>( createdProduct, HttpStatus.CREATED );
    }

    @GetMapping("/getProduct")
    @PreAuthorize( "hasAuthority('ADMIN') OR hasAuthority('USER')" )
    public ResponseEntity<?> getProduct(@RequestParam String name){
        Product product=productService.findProduct(name);
        return new ResponseEntity<>( product, HttpStatus.OK );
    }

    @GetMapping("/getProducts")
    @PreAuthorize( "hasAuthority('ADMIN') OR hasAuthority('USER')" )
    public ResponseEntity<?> getProducts(){
        List<Product> product=productService.findProducts();
        return new ResponseEntity<>( product, HttpStatus.OK );
    }


}
