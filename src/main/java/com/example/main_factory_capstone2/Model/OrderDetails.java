package com.example.main_factory_capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private Integer order_id;
    @Column(columnDefinition = "int not null")
    private Integer product_id;
    @NotEmpty(message = "Cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String quantity;
}
