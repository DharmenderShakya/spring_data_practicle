package com.spring_data_projections_practicle.service;

import org.springframework.stereotype.Service;

import com.spring_data_projections_practicle.entity.Department;
import com.spring_data_projections_practicle.entity.Employee;
import com.spring_data_projections_practicle.projection.EmployeeProjection;

import java.util.List;


public interface EmployeeService {


    // Employee CRUD
    public Employee saveEmployee(Employee emp);

    public List<Employee> getAllEmployees() ;

    public Employee getEmployee(Long id) ;

    public void deleteEmployee(Long id);

    // Projection
    public List<EmployeeProjection> getEmployeeProjection() ;

    // Department CRUD
    public Department saveDepartment(Department dept) ;
    
    public List<Department> getAllDepartments() ;
    
}