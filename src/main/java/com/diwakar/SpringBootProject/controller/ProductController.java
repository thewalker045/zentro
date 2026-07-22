package com.diwakar.SpringBootProject.controller;

import com.diwakar.SpringBootProject.model.Product;
import com.diwakar.SpringBootProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){

        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product=service.getProductById(id);
        if(product!=null) return new ResponseEntity<>(product,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @GetMapping("/products/{productId}/image")
   public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
        Product product=service.getProductById(id);
        byte[] imageFile=product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
   }
   @PutMapping("/products/{id}")
   public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile imageFile){
        try {
            Product product1 = service.updateProduct(id, product, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("an error occurred while updating",HttpStatus.OK);
        }
        if(product!=null)
            return new ResponseEntity<>("updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("cant update",HttpStatus.OK);
   }

   @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product=service.getProductById(id);
        if(product!=null){
            service.DeleteProductById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
   }
        else{
            return new ResponseEntity<>("Product not found",HttpStatus.OK);
        }
    }
}
