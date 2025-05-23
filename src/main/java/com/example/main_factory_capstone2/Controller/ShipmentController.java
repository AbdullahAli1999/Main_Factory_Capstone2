package com.example.main_factory_capstone2.Controller;

import com.example.main_factory_capstone2.Api.ApiResponse;
import com.example.main_factory_capstone2.Model.Order;
import com.example.main_factory_capstone2.Model.Shipment;
import com.example.main_factory_capstone2.Model.User;
import com.example.main_factory_capstone2.Repository.ShipmentRepository;
import com.example.main_factory_capstone2.Service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shipment")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllShipment(){
        return ResponseEntity.status(200).body(shipmentService.getAllShipmets());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addShipment(@RequestBody @Valid Shipment shipment, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(shipmentService.addShipment(shipment)) {
            return ResponseEntity.status(200).body(new ApiResponse("Shipment Added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateShipment(@PathVariable Integer id, @RequestBody @Valid Shipment shipment, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = shipmentService.updateShipment(id, shipment);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delShipment(@PathVariable Integer id){
        return ResponseEntity.status(200).body(shipmentService.deleteShipment(id));
    }
    //2.Track
    @GetMapping("/track/{track}")
    public ResponseEntity tracking(@PathVariable String track){
        Shipment trackByNumber = shipmentService.tracking(track);
        if(trackByNumber == null){
            return ResponseEntity.status(400).body(new ApiResponse("Not found"));
        }
        return ResponseEntity.status(200).body(trackByNumber);
    }
    //12.
    @PutMapping("/mark/{id}")
    public ResponseEntity markDelivered(@PathVariable Integer id){
        Shipment shipment = shipmentService.markDelivered(id);

        if(shipment == null){
            return ResponseEntity.status(400).body(new ApiResponse("Shipment not found or is not in transit."));
        }
        return ResponseEntity.status(200).body(shipment);
    }
}
