package com.diwakar.SpringBootProject.service;

import com.diwakar.SpringBootProject.dto.CartRequest;
import com.diwakar.SpringBootProject.model.Cart;
import com.diwakar.SpringBootProject.model.Product;
import com.diwakar.SpringBootProject.model.Users;
import com.diwakar.SpringBootProject.repository.CartRepo;
import com.diwakar.SpringBootProject.repository.ProductRepo;
import com.diwakar.SpringBootProject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    UserRepo UserRepo;
    @Autowired
    ProductRepo ProductRepo;
    @Autowired
    CartRepo CartRepo;
    public void addCart(CartRequest request) {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email=authentication.getName();
        Users user=UserRepo.findByEmail(email)
                .orElseThrow();
        Product product = ProductRepo.findById(request.getProductID())
                .orElseThrow();
        Optional<Cart> existing= CartRepo.findByUserAndProduct(user,product);

        if(existing.isPresent()){

            Cart cart= existing.get();
            cart.setQuantity(
                    cart.getQuantity() + request.getQuantity()
            );

        }
        else{
            Cart cart=new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(request.getQuantity());


            CartRepo.save(cart);


        }



    }
}
