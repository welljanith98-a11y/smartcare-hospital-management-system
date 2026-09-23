package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.Department;
import com.smartcare.smartcare.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Integer id, Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with id: " + id));

        department.setDepartmentName(departmentDetails.getDepartmentName());
        department.setLocation(departmentDetails.getLocation());
        department.setHeadDoctor(departmentDetails.getHeadDoctor());

        return departmentRepository.save(department);
    }

    public void deleteDepartment(Integer id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found with id: " + id);
        }

        departmentRepository.deleteById(id);
    }
}