package com.example.main_factory_capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @Column(columnDefinition = "int not null")
    private Integer productId;
    @Column(columnDefinition = "int not null")
    private Integer rating; // e.g., 1 to 5
    @NotEmpty(message = "Comment cannot be empty")
    @Size(max = 255, message = "Comment can't exceed 255 characters")
    @Column(columnDefinition = "varchar(255) not null")
    private String comment;
    @Column(columnDefinition = "DATE")
    private LocalDate reviewDate;
    //@NotEmpty(message = "Product Name cannot be empty")
    @Size(max = 255, message = "Product Name can't exceed 255 characters")
    @Column(columnDefinition = "varchar(255) not null")
    private String productName;

}
