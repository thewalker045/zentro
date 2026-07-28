package com.diwakar.SpringBootProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.diwakar.SpringBootProject.model.Product;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
