package com.spring_data_projections_practicle.service;

import java.util.List;

import com.spring_data_projections_practicle.entity.Department;

public interface DepartmentService {

	public Department createDepartment(Department dept);

    // READ ALL
    public List<Department> getAllDepartments();

    // READ BY ID
    public Department getDepartment(Long id);

    // UPDATE
    public Department updateDepartment(Long id, Department dept) ;

    // DELETE
    public void deleteDepartment(Long id) ;
	
}
