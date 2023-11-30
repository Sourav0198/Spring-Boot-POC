package com.example.postgreespringrest.services;

import com.example.postgreespringrest.entity.EmployeeEntity;
import com.example.postgreespringrest.error.IdNotFoundException;
import com.example.postgreespringrest.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

//@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplementTest {
    @InjectMocks
    private EmployeeServiceImplement employeeService = new EmployeeServiceImplement();

    @Mock
    private EmployeeRepository employeeRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        EmployeeEntity employee =
//                EmployeeEntity.builder()
//                        .id(9000L)
//                        .address("Ahmedabad")
//                        .designation("HR")
//                        .name("Sourav")
//                        .phone_no("8999")
//                        .build();



    }

    @Test

    @DisplayName("Get Id when Id actually exist in the database")
    public void whenIdFoundThenReturnId() throws IdNotFoundException {
        EmployeeEntity employee=new EmployeeEntity(9000L,"sourav", "9000","Kolkata","HR");

        Mockito.when(employeeRepository.findById(9000))
                .thenReturn(Optional.ofNullable(employee));
        int employeeId = 9000;
       Optional<EmployeeEntity> found =
                employeeService.findEmployeeById(employeeId);

        assertEquals(employeeId, found.get().getId());
//        assertEquals(200,200);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
//
    void  WhenEmployeeActaullyExist_ThenReturnTrue() {
        Mockito.when(employeeRepository.isEmployeeExistById(9000)).thenReturn(true);
        int employeeId = 9000;
        boolean flag =
                employeeService.isEmployeeExistByIdService(employeeId);

       assertTrue(flag);
    }
    @Test
    void  WhenEmployeeDoesnotExist_ThenReturnFalse() {
        int employeeId = 9001;
        boolean flag =
                employeeService.isEmployeeExistByIdService(employeeId);

        assertFalse(flag);
    }


    //check this..

    @Test
    void deleteEmployee() {
        int employeeId = 1;

//        when(employeeRepository.existsById(employeeId)).thenReturn(true);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void findAllEmployee() {
        List<EmployeeEntity> mockEmployeeList = new ArrayList<>();
        // Add mock employee data to the list
        mockEmployeeList.add(new EmployeeEntity(/* Initialize with mock data */));
        mockEmployeeList.add(new EmployeeEntity(/* Initialize with mock data */));

        when(employeeRepository.findAll()).thenReturn(mockEmployeeList);

        List<EmployeeEntity> resultEmployeeList = employeeService.findAllEmployee();

        assertThat(resultEmployeeList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(mockEmployeeList.size())
                .containsExactlyElementsOf(mockEmployeeList);
    }

    @Test
    void findEmployeeById() throws IdNotFoundException {
        Optional<EmployeeEntity> employee= Optional.of(new EmployeeEntity(2L, "Sourav", "2311", "Kolkata", "ASE"));

        when(employeeRepository.findById(20)).thenReturn(employee);

        Optional<EmployeeEntity> resultEmployee = employeeService.findEmployeeById(20);

        assertThat(resultEmployee)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(employee);

    }

    @Test
    void addEmployee() {
        EmployeeEntity mockEmployee = new EmployeeEntity(/* Initialize with mock data */);

        when(employeeRepository.save( mockEmployee)).thenReturn(mockEmployee);

        EmployeeEntity resultEmployee = employeeService.addEmployee(mockEmployee);

        assertThat(resultEmployee).isNotNull();
        assertThat(resultEmployee).isEqualTo(mockEmployee);
    }

    @Test
    void updateEmployee() {
        EmployeeEntity mockEmployee = new EmployeeEntity(/* Initialize with mock data */);

        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(mockEmployee);

        EmployeeEntity updatedEmployee = employeeService.updateEmployee(mockEmployee);

        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee).isEqualTo(mockEmployee);
    }

    @Test
    void testDeleteEmployee() {
        int employeeId = 1;

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void deleteMultipleRows() {
    }

    @Test
    void testIsEmployeeExistByIdService() {
        int employeeId = 1;

        when(employeeRepository.isEmployeeExistById(employeeId)).thenReturn(true);

        boolean result = employeeService.isEmployeeExistByIdService(employeeId);

//        assertThat(result).isTrue();
        assertTrue(result);
    }
}