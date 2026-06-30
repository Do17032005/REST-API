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

    @Override
    public Product AddProduct(Product product){
        Optional<Product> max = Products.stream().max(Comparator.comparing(Product::getId));
        int newId = max.isPresent() ? max.get().getId() + 1 : 1;
        Product temp = new Product(newId, product.getName(), product.getPrice());
        products.add(temp);
        return temp;
    }

    @Override
    public Product UpdateProduct(Product product){
        Product update = findById(product.getId());
        if (update != null) {
            update.setName(product.getName());
            update.setPrice(product.getPrice());
        }
        return update;
    }

    @Override
    public Product DeleteProduct(int id){
        Product delete = findById(id);
        if (delete != null) {
            products.remove(delete);
        }
        return delete;
    }

}
