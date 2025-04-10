package com.example.main_factory_capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 4,message = "Length must be more than 4")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @Email
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 8,message = "Length must be more than 8")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;
    @Column(columnDefinition = ("DATE"))
    private LocalDate created_at;

}
