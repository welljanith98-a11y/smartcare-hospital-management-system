package com.smartcare.smartcare.controller;

import com.smartcare.smartcare.entity.Admission;
import com.smartcare.smartcare.service.AdmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    private final AdmissionService admissionService;

    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @GetMapping
    public List<Admission> getAllAdmissions() {
        return admissionService.getAllAdmissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(
            @PathVariable Integer id) {

        return admissionService.getAdmissionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Admission> createAdmission(
            @RequestBody Admission admission) {

        return ResponseEntity.ok(
                admissionService.createAdmission(admission)
        );
    }

    @PutMapping("/{id}/discharge")
    public ResponseEntity<Admission> dischargePatient(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                admissionService.dischargePatient(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmission(
            @PathVariable Integer id) {

        admissionService.deleteAdmission(id);
        return ResponseEntity.noContent().build();
    }
}