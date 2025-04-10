package com.example.main_factory_capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 4,message = "Length must be more than 4")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @Column(columnDefinition = "int not null")
    private Integer factory_id;
    @NotNull(message = "Cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer price;
    @NotNull(message = "Cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer quantity_in_stock;
    @NotEmpty(message = "Cannot be empty")
    @Size(max = 200,message = "Length must be less than or equal 200")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;



}
