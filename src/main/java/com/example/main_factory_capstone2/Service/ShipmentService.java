package com.example.main_factory_capstone2.Service;

import com.example.main_factory_capstone2.Model.*;
import com.example.main_factory_capstone2.Repository.OrderRepository;
import com.example.main_factory_capstone2.Repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;

    //GET
    public List<Shipment> getAllShipmets() {
        return shipmentRepository.findAll();
    }
    //ADD
    public boolean addShipment(Shipment shipment){
        Order oid = orderRepository.findOrderById(shipment.getOrder_id());
        if(oid.getId().equals(shipment.getOrder_id())){
            shipmentRepository.save(shipment);
            return true;
        }
        return false;
    }
    //UPDATE
    public boolean updateShipment(Integer id , Shipment shipment){
        Shipment oldShipment = shipmentRepository.findShipmentById(id);
        if(oldShipment == null){
            return false;
        }
        oldShipment.setTracking_number(shipment.getTracking_number());
        oldShipment.setStatus(shipment.getStatus());
        oldShipment.setShipment_date(LocalDate.now());
        shipmentRepository.save(oldShipment);
        return true;
    }
    //DELETE
    public boolean deleteShipment(Integer id){
        Shipment delShipment = shipmentRepository.findShipmentById(id);
        if(delShipment == null){
            return false;
        }
        shipmentRepository.delete(delShipment);
        return true;
    }

    //2.Tracking By number
    public Shipment tracking(String track){
       return shipmentRepository.byTrackingNumber(track);
    }


}
