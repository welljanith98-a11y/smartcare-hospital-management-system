package com.smartcare.smartcare.controller;

import com.smartcare.smartcare.entity.Doctor;
import com.smartcare.smartcare.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(
            @RequestBody Doctor doctor) {
        return ResponseEntity.ok(
                doctorService.createDoctor(doctor)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Integer id,
            @RequestBody Doctor doctor) {

        return ResponseEntity.ok(
                doctorService.updateDoctor(id, doctor)
        );
    }
    @GetMapping("/search")
public List<Doctor> searchDoctors(
        @RequestParam String name) {
    return doctorService.searchDoctorsByName(name);
}
@GetMapping("/search-specialization")
public List<Doctor> searchDoctorsBySpecialization(
        @RequestParam String specialization) {
    return doctorService.searchDoctorsBySpecialization(specialization);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}