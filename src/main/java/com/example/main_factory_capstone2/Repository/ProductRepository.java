package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findProductById(Integer id);
    @Query("select p from Product p where p.factory_id = ?1")
    List<Product> findProductByFactory_id(Integer id);
}
