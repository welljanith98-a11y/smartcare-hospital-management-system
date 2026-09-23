package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.Patient;
import com.smartcare.smartcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByFullNameContainingIgnoreCase(name);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Integer id, Patient patientDetails) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found with id: " + id));

        patient.setFullName(patientDetails.getFullName());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        patient.setAddress(patientDetails.getAddress());
        patient.setContactNumber(patientDetails.getContactNumber());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        patient.setEmergencyContact(patientDetails.getEmergencyContact());

        return patientRepository.save(patient);
    }

    public void deletePatient(Integer id) {

        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with id: " + id);
        }

        patientRepository.deleteById(id);
    }
}