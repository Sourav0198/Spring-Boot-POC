package com.example.postgreespringrest.controller;

import com.example.postgreespringrest.entity.EmployeeEntity;
import com.example.postgreespringrest.error.IdNotFoundException;
import com.example.postgreespringrest.services.EmployeeServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private final EmployeeServiceInterface employeeServiceInterface;

    public EmployeeController(EmployeeServiceInterface employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }

    @PostMapping("/bulk")
    public String addBulkEmployees() {
        Long totalEmployees = 4000L;

        for (Long i = 2001L; i <= totalEmployees; i++) {
            EmployeeEntity employee = generateEmployee(i);
            this.employeeServiceInterface.addEmployee(employee);
        }

        return "Bulk employees added successfully.";
    }

    private EmployeeEntity generateEmployee(Long id) {
        // Generate random employee data
        Long Id=id;

        String name = "Employee " + id;
        String phone_no = "Phone " + id;
        String address = "Address " + id;
        String designation = "Designation " + id;

        // Create and return the EmployeeEntity object
        return new EmployeeEntity(Id, name, phone_no, address, designation);
    }

    @PostMapping
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employeeEntity)
    {
        System.out.println("addEmployee of controller class");
        return this.employeeServiceInterface.addEmployee(employeeEntity);
    }
    @GetMapping
    public List<EmployeeEntity> findAllEmployee()
    {
        return this.employeeServiceInterface.findAllEmployee();
    }
    @GetMapping("/{id}")
    public Optional<EmployeeEntity> findEmployeeById(@PathVariable("id")int id) throws IdNotFoundException {
//        System.out.println("controller find employee by id called with int value:"+id.intValue());
        return this.employeeServiceInterface.findEmployeeById(id);

    }
    @GetMapping("/isexist/{id}")
    boolean isEmployeeExistById(@PathVariable("id") int id)
    {
        return this.employeeServiceInterface.isEmployeeExistByIdService(id);
    }
    @PutMapping
    public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity employeeEntity)
    {
        return this.employeeServiceInterface.updateEmployee(employeeEntity);
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable("id") int id)
    {
        this.employeeServiceInterface.deleteEmployee(id);
        return "Employee with ID= "+id+" deleted";
    }
//    @DeleteMapping
//    public String deleteEmployeeById()
//    {
//        this.employeeServiceInterface.deleteMultipleRows();
//        return "2000 rows deleted";
//    }

}
