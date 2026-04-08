package com.spring_data_projections_practicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_data_projections_practicle.entity.Employee;
import com.spring_data_projections_practicle.projection.EmployeeProjection;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Projection method
	List<EmployeeProjection> findAllProjectedBy();
}