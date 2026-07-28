package com.diwakar.SpringBootProject.dto;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class ProductRequest {

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String brand;
    private String category;

}