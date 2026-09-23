package com.smartcare.smartcare.controller;

import com.smartcare.smartcare.entity.LaboratoryTest;
import com.smartcare.smartcare.service.LaboratoryTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratory-tests")
public class LaboratoryTestController {

    private final LaboratoryTestService laboratoryTestService;

    public LaboratoryTestController(
            LaboratoryTestService laboratoryTestService) {
        this.laboratoryTestService = laboratoryTestService;
    }

    @GetMapping
    public List<LaboratoryTest> getAllTests() {
        return laboratoryTestService.getAllTests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryTest> getTestById(
            @PathVariable Integer id) {

        return laboratoryTestService.getTestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LaboratoryTest> createTest(
            @RequestBody LaboratoryTest test) {

        return ResponseEntity.ok(
                laboratoryTestService.createTest(test)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryTest> updateTest(
            @PathVariable Integer id,
            @RequestBody LaboratoryTest test) {

        return ResponseEntity.ok(
                laboratoryTestService.updateTest(id, test)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(
            @PathVariable Integer id) {

        laboratoryTestService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}