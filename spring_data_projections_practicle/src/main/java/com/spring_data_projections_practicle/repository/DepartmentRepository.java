package com.spring_data_projections_practicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_data_projections_practicle.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
