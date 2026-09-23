package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.Doctor;
import com.smartcare.smartcare.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    public List<Doctor> searchDoctorsByName(String name) {
    return doctorRepository.findByDoctorNameContainingIgnoreCase(name);
}
public List<Doctor> searchDoctorsBySpecialization(String specialization) {
    return doctorRepository.findBySpecializationContainingIgnoreCase(specialization);
}

    public Optional<Doctor> getDoctorById(Integer id) {
        return doctorRepository.findById(id);
    }

    public Doctor createDoctor(Doctor doctor) {

        if (doctor.getDoctorName() == null ||
                doctor.getDoctorName().isBlank()) {
            throw new RuntimeException("Doctor name cannot be empty");
        }

        if (doctor.getContactNumber() == null ||
                doctor.getContactNumber().isBlank()) {
            throw new RuntimeException("Contact number cannot be empty");
        }

        if (doctor.getConsultationFee() == null ||
                doctor.getConsultationFee().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Consultation fee must be greater than 0");
        }

        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Integer id, Doctor doctorDetails) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found with id: " + id));

        if (doctorDetails.getDoctorName() == null ||
                doctorDetails.getDoctorName().isBlank()) {
            throw new RuntimeException("Doctor name cannot be empty");
        }

        if (doctorDetails.getContactNumber() == null ||
                doctorDetails.getContactNumber().isBlank()) {
            throw new RuntimeException("Contact number cannot be empty");
        }

        if (doctorDetails.getConsultationFee() == null ||
                doctorDetails.getConsultationFee().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Consultation fee must be greater than 0");
        }

        doctor.setDoctorName(doctorDetails.getDoctorName());
        doctor.setQualification(doctorDetails.getQualification());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setContactNumber(doctorDetails.getContactNumber());
        doctor.setConsultationFee(doctorDetails.getConsultationFee());
        doctor.setDepartment(doctorDetails.getDepartment());

        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Integer id) {

        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }

        doctorRepository.deleteById(id);
    }
}