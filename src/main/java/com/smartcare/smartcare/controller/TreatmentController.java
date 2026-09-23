package com.smartcare.smartcare.controller;

import com.smartcare.smartcare.entity.Treatment;
import com.smartcare.smartcare.service.TreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(
            @PathVariable Integer id) {

        return treatmentService.getTreatmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Treatment> createTreatment(
            @RequestBody Treatment treatment) {

        return ResponseEntity.ok(
                treatmentService.createTreatment(treatment)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treatment> updateTreatment(
            @PathVariable Integer id,
            @RequestBody Treatment treatment) {

        return ResponseEntity.ok(
                treatmentService.updateTreatment(id, treatment)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(
            @PathVariable Integer id) {

        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }
}