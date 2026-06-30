package com.example.demo.model.entity;


import lombok.*;



// sinh ra constructor không tham số
@NoArgsConstructor
// sinh ra constructor đầy đủ tham số
@AllArgsConstructor
// sinh ra getter của tất cả thuộc tính
@Getter
// sinh ra setter của tất cả thuộc tính
@Setter


public class Product {
    private int id;
    private String name;
    private Double price;



}