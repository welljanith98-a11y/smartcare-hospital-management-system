package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.Treatment;
import com.smartcare.smartcare.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public Optional<Treatment> getTreatmentById(Integer id) {
        return treatmentRepository.findById(id);
    }

    public Treatment createTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Treatment updateTreatment(Integer id, Treatment details) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Treatment not found with id: " + id));

        treatment.setPatient(details.getPatient());
        treatment.setDoctor(details.getDoctor());
        treatment.setDiagnosis(details.getDiagnosis());
        treatment.setPrescription(details.getPrescription());
        treatment.setTreatmentNotes(details.getTreatmentNotes());
        treatment.setTreatmentDate(details.getTreatmentDate());

        return treatmentRepository.save(treatment);
    }

    public void deleteTreatment(Integer id) {
        if (!treatmentRepository.existsById(id)) {
            throw new RuntimeException("Treatment not found with id: " + id);
        }

        treatmentRepository.deleteById(id);
    }
}