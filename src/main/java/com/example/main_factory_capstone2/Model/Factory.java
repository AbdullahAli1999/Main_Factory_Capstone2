package com.example.main_factory_capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 5,message = "Length must be more than 5 ")
    @Column(columnDefinition = "varchar(30) not null")
    private String factory_name;
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 3,message = "Length must be more 3")
    @Column(columnDefinition = "varchar(30) not null")
    private String location;
    @NotEmpty(message = "Cannot be empty")
    @Size(max = 10,message = "Length must be 10 and start with 05")
    @Column(columnDefinition = "varchar(30) not null")
    private String phone;
    @NotEmpty(message = "Cannot be empty")
    @Size(max = 10,message = "Length must be 10 ")
    @Column(columnDefinition = "varchar(30) not null")
    private String commerical_register;
    @NotEmpty(message = "Cannot be empty")
    @Size(max = 10,message = "Length must be 10")
    @Column(columnDefinition = "varchar(30) not null")
    private String environmental_permit;
}
