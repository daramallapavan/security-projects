package com.example.Kalki_Project.product.service;

import com.example.Kalki_Project.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product findProduct(String name);

    List<Product> findProducts();
}
