package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.Factory;
import com.example.main_factory_capstone2.Model.Product;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Repository.FactoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FactoryService {
    private final FactoryRepository factoryRepository;

    //GET
    public List<Factory> getAllFactory() {
        return factoryRepository.findAll();
    }
    //ADD
    public void addFactory(Factory factory){
        factoryRepository.save(factory);
    }

    //UPDATE
    public boolean updateFactory(Integer id , Factory factory){
        Factory oldFactory = factoryRepository.findFactoriesById(id);
        if(oldFactory == null){
            return false;
        }
        oldFactory.setFactory_name(factory.getFactory_name());
        oldFactory.setLocation(factory.getLocation());
        oldFactory.setPhone(factory.getPhone());
        oldFactory.setCommerical_register(factory.getCommerical_register());
        oldFactory.setEnvironmental_permit(factory.getEnvironmental_permit());
        factoryRepository.save(factory);
        return true;
    }
    //DELETE
    public boolean deleteFactory(Integer id){
        Factory delFactory = factoryRepository.findFactoriesById(id);
        if(delFactory == null){
            return false;
        }
        factoryRepository.delete(delFactory);
        return true;
    }
}
