package com.university.controller;

import com.university.exception.ResourceNotFoundException;
import com.university.models.Department;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.university.repository.DepartmentRepository;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/departments")
	public List<Department> listDepartments(){
		return departmentRepository.findAll();
	}

    @PostMapping("/departments")
    public Department createDepartment(@Valid @RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/departments/{departmentId}")
    public Department updateDepartment(@PathVariable Long departmentId, @Valid @RequestBody Department departmentRequest) {
        return departmentRepository.findById(departmentId).map(department -> {
        	department.setName(departmentRequest.getName());
            return departmentRepository.save(department);
        }).orElseThrow(() -> new ResourceNotFoundException("DepartmentId " + departmentId + " not found"));
    }


    @DeleteMapping("/departments/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
        return departmentRepository.findById(departmentId).map(department -> {
        	departmentRepository.delete(department);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("DepartmentId " + departmentId + " not found"));
    }

	
}
