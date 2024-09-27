package com.example.Kalki_Project.product.serviceImpl;

import com.example.Kalki_Project.product.entity.Product;
import com.example.Kalki_Project.product.exception.ProductNotFoundException;
import com.example.Kalki_Project.product.repository.ProductRepository;
import com.example.Kalki_Project.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {

       Optional<Product> response= productRepository.findByName( product.getName() );

       if (response.isPresent()){
           throw new ProductNotFoundException( "Product exists" );
       }

      return productRepository.save( product );
    }

    @Override
    public Product findProduct(String name) {

        Optional<Product> product= productRepository.findByName( name );
        if (product.isPresent()){
            return product.get();
        }
        throw new ProductNotFoundException( " product not exist with this name "+name );
    }

    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }
}
