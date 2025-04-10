package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<Factory,Integer> {
    Factory findFactoriesById(Integer id);
}
