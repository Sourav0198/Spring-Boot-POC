package com.example.postgreespringrest.services;

import com.example.postgreespringrest.entity.EmployeeEntity;
import com.example.postgreespringrest.error.IdNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInterface {
    List<EmployeeEntity> findAllEmployee();
    Optional<EmployeeEntity> findEmployeeById(int Id) throws IdNotFoundException;
    EmployeeEntity addEmployee(EmployeeEntity employeeEntity);
    EmployeeEntity updateEmployee(EmployeeEntity employeeEntity);
    void deleteEmployee(int id);
    void deleteMultipleRows();
    boolean isEmployeeExistByIdService(int Id);


}
