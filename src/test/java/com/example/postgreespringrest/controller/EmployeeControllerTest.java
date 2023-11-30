package com.example.postgreespringrest.controller;

import com.example.postgreespringrest.entity.EmployeeEntity;
import com.example.postgreespringrest.error.IdNotFoundException;
import com.example.postgreespringrest.services.EmployeeServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeServiceInterface employeeService;
    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    @Disabled
    void setUp() throws IdNotFoundException {
        EmployeeEntity expectedEmployee = new EmployeeEntity(2L, "Employee 2",
                "Phone 2", "Address 2", "Designation 2");
        when(employeeService.findEmployeeById(2)).thenReturn(Optional.of(expectedEmployee));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addBulkEmployees() {

    }

    @Test
    void addEmployee() {
    }

    @Test
    void findAllEmployee() {
    }

    @Test
    @Disabled
    void findEmployeeById() throws IdNotFoundException {
        EmployeeEntity expectedEmployee = new EmployeeEntity(2L, "Employee 2",
                "Phone 2", "Address 2", "Designation 2");
        Optional<EmployeeEntity> employee = employeeService.findEmployeeById(2);
        assertEquals(expectedEmployee, employee.orElse(null));
    }

    @Test
    void isEmployeeExistById() throws Exception {
        int employeeId = 2;
        when(employeeService.isEmployeeExistByIdService(employeeId)).thenReturn(true);

        mockMvc.perform(get("/employee/isexist/2", employeeId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

    }

    @Test
    void updateEmployee() throws Exception {
        long employeeId = 2L;
        EmployeeEntity updatedEmployee = new EmployeeEntity(employeeId, "Updated Employee", "Updated Phone", "Updated Address", "Updated Designation");
        String updatedEmployeeJson = objectMapper.writeValueAsString(updatedEmployee);

        mockMvc.perform(MockMvcRequestBuilders.put("/employee", updatedEmployee)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeById() throws Exception {
        long employeeId = 2L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{id}", employeeId))
                .andExpect(status().isOk());
    }


    @Test
    void testFindEmployeeById() throws Exception {
        EmployeeEntity expectedEmployee = new EmployeeEntity(2L, "Employee 2", "Phone 2", "Address 2", "Designation 2");
        when(employeeService.findEmployeeById(2)).thenReturn(Optional.of(expectedEmployee));

        mockMvc.perform(get("/employee/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Employee 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone_no").value("Phone 2"));
    }

    @Test
    void testFindAllEmployee() throws Exception {
        EmployeeEntity expectedEmployee1 = new EmployeeEntity(2L, "Employee 2", "Phone 2", "Address 2", "Designation 2");
        EmployeeEntity expectedEmployee2 = new EmployeeEntity(2L, "Employee 2", "Phone 2", "Address 2", "Designation 2");

        List<EmployeeEntity> employeeList = new ArrayList<>();
        employeeList.add(expectedEmployee1);
        employeeList.add(expectedEmployee2);
        // Add mock employee data to the list

        when(employeeService.findAllEmployee()).thenReturn(employeeList);

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$").isArray());

    }


    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}