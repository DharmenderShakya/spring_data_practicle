package com.spring_data_projections_practicle;

import com.spring_data_projections_practicle.controller.EmployeeController;
import com.spring_data_projections_practicle.entity.Department;
import com.spring_data_projections_practicle.entity.Employee;
import com.spring_data_projections_practicle.projection.EmployeeProjection;
import com.spring_data_projections_practicle.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee emp;
    private Department dept;

    @BeforeEach
    void setUp() {
        dept = new Department();
        dept.setId(1L);
        dept.setName("HR");

        emp = new Employee();
        emp.setId(1L);
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setPosition("Manager");
        emp.setSalary(5000.0);
        emp.setDepartment(dept);
    }

    @Test
    void testCreateEmployee() throws Exception {
        when(service.saveEmployee(any(Employee.class))).thenReturn(emp);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.department.name").value("HR"));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        when(service.getAllEmployees()).thenReturn(Arrays.asList(emp));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(service.getEmployee(1L)).thenReturn(emp);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    void testGetEmployeeProjection() throws Exception {
      
        EmployeeProjection projection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "John Doe";
            }

            @Override
            public String getPosition() {
                return "Manager";
            }

            @Override
            public String getDepartmentName() {
                return "HR";
            }
        };

        List<EmployeeProjection> projections = Arrays.asList(projection);

        // Mock the service to return this list
        when(service.getEmployeeProjection()).thenReturn(projections);

        mockMvc.perform(get("/api/employees/projection"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("John Doe"))
                .andExpect(jsonPath("$[0].position").value("Manager"))
                .andExpect(jsonPath("$[0].departmentName").value("HR"));
    }

    @Test
    void testCreateDepartmentViaEmployeeController() throws Exception {
        when(service.saveDepartment(any(Department.class))).thenReturn(dept);

        mockMvc.perform(post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dept)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    void testGetDepartmentsViaEmployeeController() throws Exception {
        when(service.getAllDepartments()).thenReturn(Arrays.asList(dept));

        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("HR"));
    }
}
