package com.example.demo.model.service.impl;

import com.example.demo.model.entity.Product;
import com.example.demo.model.service.IProductService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    List<Product> products = new ArrayList<>(
        List.of(
            new Product(1, "Máy tính", 10.0),
            new Product(2, "Sách", 20.0),
            new Product(3, "Bút", 30.0),
            new Product(4, "Vở", 40.0),
            new Product(5, "Điện thoại", 50.0)
        )
    );

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

}
