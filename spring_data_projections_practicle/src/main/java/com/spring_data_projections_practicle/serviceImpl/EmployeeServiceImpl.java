package com.spring_data_projections_practicle.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring_data_projections_practicle.entity.Department;
import com.spring_data_projections_practicle.entity.Employee;
import com.spring_data_projections_practicle.projection.EmployeeProjection;
import com.spring_data_projections_practicle.repository.DepartmentRepository;
import com.spring_data_projections_practicle.repository.EmployeeRepository;
import com.spring_data_projections_practicle.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepo;
	
    private final DepartmentRepository departmentRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo,
                           DepartmentRepository departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    // Employee CRUD
    public Employee saveEmployee(Employee emp) {
        return employeeRepo.save(emp);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeRepo.findById(id).orElseThrow();
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    // Projection
    public List<EmployeeProjection> getEmployeeProjection() {
        return employeeRepo.findAllProjectedBy();
    }

    // Department CRUD
    public Department saveDepartment(Department dept) {
        return departmentRepo.save(dept);
    }

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

}
