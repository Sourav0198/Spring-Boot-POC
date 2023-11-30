package com.example.postgreespringrest.repository;

import com.example.postgreespringrest.entity.EmployeeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TestEntityManager entityManager;


    @BeforeEach
    @Disabled
//    void setUp() {
//        System.out.println("before each test it gets executed");
//        EmployeeEntity employee= EmployeeEntity.builder().phone_no("2001").id(91110L).
//                address("Kolkata").name("Sourav")
//                .designation("ASE").build();
//        entityManager.persist(employee);
//
//    }


    @Test
    void verifyBootstrappingByPersistingAnEmployee() {

        EmployeeEntity emp= EmployeeEntity.builder().phone_no("2001").id(91110L).
                address("Kolkata").name("Sourav")
                .designation("ASE").build();
        Assertions.assertNull(emp.getId());

        entityManager.persist(emp);
        Assertions.assertNotNull(emp.getId());
    }









    @Test
    public void testFindEmployeeById() {
        int employeeId = 1;
        Optional<EmployeeEntity> employee=employeeRepository.findById(91110);
        assertEquals(employee.get().getName(),"Sourav");
//
//        assertThat(foundEmployee.isPresent()).isTrue();
//        assertThat(foundEmployee.get()).isEqualTo(mockEmployee);
    }


    @Test
    void deleteByCustumQuery() {
    }

    @Test
    void isEmployeeExistById() {
    }
}