package com.example.main_factory_capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "orders")
@Check(constraints = "( status='PENDING' or status='PROCESSING' or status='SHIPPED' or status='CANCELLED' or status='RETURNED')")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private Integer user_id;
    @Column(columnDefinition = "DATE")
    private LocalDate order_date;
    @NotEmpty(message = "Cannot be empty")
    @Pattern(regexp = "^(?i)(PENDING|PROCESSING|SHIPPED|CANCELLED|RETURNED)$", message = "status must be 'PENDING' or 'PROCESSING' or 'SHIPPED' or 'CANCELLED'")
    private String status;
}
