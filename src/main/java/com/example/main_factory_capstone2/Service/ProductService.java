package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.*;
import com.example.main_factory_capstone2.Repository.FactoryRepository;
import com.example.main_factory_capstone2.Repository.ProductRepository;
import com.example.main_factory_capstone2.Repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final FactoryRepository factoryRepository;


    //GET
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //ADD
    public boolean addProduct(Product product){
        Factory fid = factoryRepository.findFactoriesById(product.getFactory_id());
        if(fid.getId().equals(product.getFactory_id())){
            productRepository.save(product);
            return true;
        }
        return false;
    }
    //UPDATE
    public boolean updateProduct(Integer id , Product product){
        Product oldProduct = productRepository.findProductById(id);
        if(oldProduct == null){
            return false;
        }
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity_in_stock(product.getQuantity_in_stock());
        oldProduct.setDescription(product.getDescription());
        productRepository.save(oldProduct);
        return true;
    }

    //DELETE
    public boolean deleteProduct(Integer id){
        Product delProduct = productRepository.findProductById(id);
        if(delProduct == null){
            return false;
        }
        productRepository.delete(delProduct);
        return true;
    }
}
