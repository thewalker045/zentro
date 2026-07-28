package com.diwakar.SpringBootProject.service;

import com.diwakar.SpringBootProject.dto.ProductRequest;
import com.diwakar.SpringBootProject.model.Product;
import com.diwakar.SpringBootProject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
    @Autowired
    private SupabaseStorageService supabaseService;
    public List<Product> getProducts() {
        return repo.findAll();
    }

    public void addProducts(ProductRequest request, MultipartFile image) throws IOException {

         String imageurl=supabaseService.uploadImage(image);

         Product product =new Product();
         //creating product object to combine the data
         product.setName(request.getName());
         product.setCategory(request.getCategory());
         product.setBrand((request.getBrand()));
         product.setDescription(request.getDescription());
         product.setPrice(request.getPrice());
         product.setStock(request.getStock());
         product.setImageUrl(imageurl);

         repo.save(product);
    }

    public void updateProducts(ProductRequest request, MultipartFile image,Integer id) throws IOException {

            Product product= repo.findById(id).orElseThrow(()->new RuntimeException("product not found"));

        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setBrand((request.getBrand()));
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        if(image!=null &&!image.isEmpty()){
            String imageurl=supabaseService.uploadImage(image);

            product.setImageUrl(imageurl);
        }

        repo.save(product);

    }

    public Product getProductById(Integer id) {
        return repo.findById(id).orElseThrow(()->new RuntimeException("product Not found"));
    }

    public void deleteProducts(Integer id) {
        repo.findById(id).orElseThrow(()->new RuntimeException("Product does not exists"));
        repo.deleteById(id);

    }
}
