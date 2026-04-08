package com.spring_data_projections_practicle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring_data_projections_practicle.entity.Department;
import com.spring_data_projections_practicle.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department dept) {
        return ResponseEntity.status(201).body(service.createDepartment(dept));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(service.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getDepartment(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Department not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Department dept) {
        try {
            return ResponseEntity.ok(service.updateDepartment(id, dept));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Department not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }
}
