package com.diwakar.SpringBootProject.repository;

import com.diwakar.SpringBootProject.model.Cart;
import com.diwakar.SpringBootProject.model.Product;
import com.diwakar.SpringBootProject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUserAndProduct(Users user, Product product);
}
