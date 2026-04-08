package com.spring_data_projections_practicle.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring_data_projections_practicle.entity.Department;
import com.spring_data_projections_practicle.repository.DepartmentRepository;
import com.spring_data_projections_practicle.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository repo;

    public DepartmentServiceImpl(DepartmentRepository repo) {
        this.repo = repo;
    }

    public Department createDepartment(Department dept) {
        return repo.save(dept);
    }

    public List<Department> getAllDepartments() {
        return repo.findAll();
    }

    public Department getDepartment(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department updateDepartment(Long id, Department dept) {
        Department existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existing.setName(dept.getName());

        return repo.save(existing);
    }

    public void deleteDepartment(Long id) {
        repo.deleteById(id);
    }
}
