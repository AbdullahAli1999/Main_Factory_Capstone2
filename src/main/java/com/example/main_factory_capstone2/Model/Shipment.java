package com.example.main_factory_capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "( status='PENDING' or status='IN_TRANSIT' or status='DELIVERED' or status='CANCELLED')")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private Integer order_id;
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 4,message = "Length must be more than 4")
    @Column(columnDefinition = "varchar(30) not null")
    private String tracking_number;
    @Column(columnDefinition = "DATE")
    private LocalDate shipment_date;
    @NotEmpty(message = "Cannot be empty")
    @Pattern(regexp = "^(?i)(PENDING|IN_TRANSIT|DELIVERED|CANCELLED)$", message = "status must be 'PENDING' or 'IN_TRANSIT' or 'DELIVERED' or 'CANCELLED'")
    private String status;
}
