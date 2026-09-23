package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.Appointment;
import com.smartcare.smartcare.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Appointment appointment) {

        if (appointment.getAppointmentDate() == null) {
            throw new RuntimeException("Appointment date is required");
        }

        if (appointment.getAppointmentTime() == null) {
            throw new RuntimeException("Appointment time is required");
        }

        if (appointment.getDoctor() == null ||
                appointment.getDoctor().getDoctorId() == null) {
            throw new RuntimeException("Doctor is required");
        }

        if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
            throw new RuntimeException(
                    "Appointment date cannot be in the past");
        }

        boolean conflict =
                appointmentRepository
                        .existsByDoctorDoctorIdAndAppointmentDateAndAppointmentTime(
                                appointment.getDoctor().getDoctorId(),
                                appointment.getAppointmentDate(),
                                appointment.getAppointmentTime()
                        );

        if (conflict) {
            throw new RuntimeException(
                    "Doctor already has an appointment at this date and time"
            );
        }

        if (appointment.getAppointmentStatus() == null ||
                appointment.getAppointmentStatus().isBlank()) {
            appointment.setAppointmentStatus("SCHEDULED");
        }

        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(
            Integer id,
            Appointment appointmentDetails) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Appointment not found with id: " + id));

        if (appointmentDetails.getAppointmentDate() == null) {
            throw new RuntimeException("Appointment date is required");
        }

        if (appointmentDetails.getAppointmentTime() == null) {
            throw new RuntimeException("Appointment time is required");
        }

        if (appointmentDetails.getDoctor() == null ||
                appointmentDetails.getDoctor().getDoctorId() == null) {
            throw new RuntimeException("Doctor is required");
        }

        if (appointmentDetails.getAppointmentDate()
                .isBefore(LocalDate.now())) {
            throw new RuntimeException(
                    "Appointment date cannot be in the past");
        }

        boolean conflict =
                appointmentRepository
                        .existsByDoctorDoctorIdAndAppointmentDateAndAppointmentTimeAndAppointmentIdNot(
                                appointmentDetails.getDoctor().getDoctorId(),
                                appointmentDetails.getAppointmentDate(),
                                appointmentDetails.getAppointmentTime(),
                                id
                        );

        if (conflict) {
            throw new RuntimeException(
                    "Doctor already has an appointment at this date and time");
        }

        appointment.setPatient(appointmentDetails.getPatient());

        appointment.setDoctor(appointmentDetails.getDoctor());

        appointment.setAppointmentDate(
                appointmentDetails.getAppointmentDate());

        appointment.setAppointmentTime(
                appointmentDetails.getAppointmentTime());

        appointment.setConsultationRoom(
                appointmentDetails.getConsultationRoom());

        appointment.setAppointmentStatus(
                appointmentDetails.getAppointmentStatus());

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Integer id) {

        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException(
                    "Appointment not found with id: " + id);
        }

        appointmentRepository.deleteById(id);
    }
}