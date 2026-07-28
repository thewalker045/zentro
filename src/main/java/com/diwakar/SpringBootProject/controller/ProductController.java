package com.diwakar.SpringBootProject.controller;

import com.diwakar.SpringBootProject.dto.ProductRequest;
import com.diwakar.SpringBootProject.model.Product;
import com.diwakar.SpringBootProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(
                service.getProducts(),
                HttpStatus.OK
        );
    }

    @PostMapping("/addProducts")
    public ResponseEntity<String> addProducts(@RequestPart ProductRequest request,
                                              @RequestPart MultipartFile image)
            throws IOException
    {
        service.addProducts(request,image);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping ("/updateProducts/{id}")
    public ResponseEntity<String> updateProducts(@RequestPart ProductRequest request,
                                                 @RequestPart(required = false) MultipartFile image,
                                                 @PathVariable Integer id)
            throws IOException{
        service.updateProducts(request,image,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Integer id){
        return new ResponseEntity<>(
                service.getProductById(id),
                HttpStatus.OK);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProducts(Integer id){
        service.deleteProducts(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
