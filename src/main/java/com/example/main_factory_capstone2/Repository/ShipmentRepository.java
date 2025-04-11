package com.example.main_factory_capstone2.Repository;

import com.example.main_factory_capstone2.Model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {
    Shipment findShipmentById(Integer id);
    @Query("select s from Shipment  s where  s.tracking_number = ?1")
    Shipment byTrackingNumber(String track);
}
