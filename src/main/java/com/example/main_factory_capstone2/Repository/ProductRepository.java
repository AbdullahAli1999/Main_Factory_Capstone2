package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findProductById(Integer id);
}
