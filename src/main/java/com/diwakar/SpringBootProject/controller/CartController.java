package com.diwakar.SpringBootProject.controller;

import com.diwakar.SpringBootProject.dto.CartRequest;
import com.diwakar.SpringBootProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
@Autowired
CartService service;
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestPart CartRequest request){
        service.addCart(request);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/delete/{CartId}")
    public ResponseEntity<String> deleteFromCart(@PathVariable int CartId){
        service.removeCart(CartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
