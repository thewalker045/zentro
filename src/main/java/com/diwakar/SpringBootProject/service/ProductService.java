package com.diwakar.SpringBootProject.service;

import com.diwakar.SpringBootProject.model.Product;
import com.diwakar.SpringBootProject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }


}
