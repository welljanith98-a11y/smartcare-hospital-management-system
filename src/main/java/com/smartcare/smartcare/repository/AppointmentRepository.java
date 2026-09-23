package com.smartcare.smartcare.repository;

import com.smartcare.smartcare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    boolean existsByDoctorDoctorIdAndAppointmentDateAndAppointmentTime(
            Integer doctorId,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );
    boolean existsByDoctorDoctorIdAndAppointmentDateAndAppointmentTimeAndAppointmentIdNot(
        Integer doctorId,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        Integer appointmentId
);
}