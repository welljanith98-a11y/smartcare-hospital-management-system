package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.Admission;
import com.smartcare.smartcare.entity.Room;
import com.smartcare.smartcare.repository.AdmissionRepository;
import com.smartcare.smartcare.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final RoomRepository roomRepository;

    public AdmissionService(
            AdmissionRepository admissionRepository,
            RoomRepository roomRepository) {

        this.admissionRepository = admissionRepository;
        this.roomRepository = roomRepository;
    }

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    public Optional<Admission> getAdmissionById(Integer id) {
        return admissionRepository.findById(id);
    }

    public Admission createAdmission(Admission admission) {

        if (admission.getRoom() == null ||
                admission.getRoom().getRoomId() == null) {
            throw new RuntimeException("Room is required");
        }

        Room room = roomRepository.findById(
                admission.getRoom().getRoomId()
        ).orElseThrow(() ->
                new RuntimeException("Room not found"));

        if (!"AVAILABLE".equalsIgnoreCase(
                room.getAvailabilityStatus())) {

            throw new RuntimeException(
                    "Room is not available for admission");
        }

        if (admission.getAdmissionDate() == null) {
            admission.setAdmissionDate(LocalDate.now());
        }

        if (admission.getAdmissionStatus() == null ||
                admission.getAdmissionStatus().isBlank()) {

            admission.setAdmissionStatus("ADMITTED");
        }

        room.setAvailabilityStatus("OCCUPIED");
        roomRepository.save(room);

        return admissionRepository.save(admission);
    }

    public Admission dischargePatient(Integer id) {

        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Admission not found with id: " + id));

        admission.setDischargeDate(LocalDate.now());
        admission.setAdmissionStatus("DISCHARGED");

        Room room = admission.getRoom();

        if (room != null) {
            room.setAvailabilityStatus("AVAILABLE");
            roomRepository.save(room);
        }

        return admissionRepository.save(admission);
    }

    public void deleteAdmission(Integer id) {

        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Admission not found with id: " + id));

        Room room = admission.getRoom();

        if (room != null &&
                "OCCUPIED".equalsIgnoreCase(
                        room.getAvailabilityStatus())) {

            room.setAvailabilityStatus("AVAILABLE");
            roomRepository.save(room);
        }

        admissionRepository.deleteById(id);
    }
}