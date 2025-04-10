package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {
    Shipment findShipmentById(Integer id);
}
