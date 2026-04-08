package com.spring_data_projections_practicle.controller;

import org.springframework.web.bind.annotation.*;

import com.spring_data_projections_practicle.entity.Department;
import com.spring_data_projections_practicle.entity.Employee;
import com.spring_data_projections_practicle.projection.EmployeeProjection;
import com.spring_data_projections_practicle.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee emp) {
        return service.saveEmployee(emp);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return service.getEmployee(id);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "Deleted";
    }
    
    @GetMapping("/employees/projection")
    public List<EmployeeProjection> getProjection() {
        return service.getEmployeeProjection();
    }

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department dept) {
        return service.saveDepartment(dept);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return service.getAllDepartments();
    }
}