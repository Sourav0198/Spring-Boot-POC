package com.example.postgreespringrest.services;

import com.example.postgreespringrest.entity.EmployeeEntity;
import com.example.postgreespringrest.error.IdNotFoundException;
import com.example.postgreespringrest.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplement implements EmployeeServiceInterface{
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeServiceImplement() {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override

    public Optional<EmployeeEntity> findEmployeeById(int id) throws IdNotFoundException {
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(id);
        if(!employeeEntity.isPresent())
        {
            throw new IdNotFoundException("Employee Id not found!");
        }
        return employeeEntity;
    }



    @Override
    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
        System.out.println("Add employee method is being called in service layer");
        return  employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity) {
        return  employeeRepository.save(employeeEntity);
    }

    @Override

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteMultipleRows() {
        this.employeeRepository.deleteByCustumQuery();

    }

    @Override
    public boolean isEmployeeExistByIdService(int Id) {
        System.out.println("isEmployeeExistByIdService() called in service layer");

        return this.employeeRepository.isEmployeeExistById(Id);
    }
}
