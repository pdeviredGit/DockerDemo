package com.learning.springcloud.repos;

import com.learning.springcloud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
