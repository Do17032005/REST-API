package com.example.demo.model.service;

import com.example.demo.model.entity.Product;
import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product AddProduct(Product product);

    Product UpdateProduct(Product product);

    Product DeleteProduct(int id);

}