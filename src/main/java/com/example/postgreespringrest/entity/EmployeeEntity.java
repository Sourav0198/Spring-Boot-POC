package com.example.postgreespringrest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder



public class EmployeeEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "emp_id")
    private Long id;
    @Column(name = "emp_name")
    private String name;
    @Column(name = "emp_phone")
    private String phone_no;
    @Column(name = "emp_address")
    private String address;
    @Column(name = "emp_designation")
    String designation;

}