package com.diwakar.SpringBootProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemResponse {

    private Integer cartId;

    private Integer productId;

    private String productName;

    private String brand;

    private String imageUrl;

    private Double price;

    private Integer quantity;

    private Double subtotal;

}