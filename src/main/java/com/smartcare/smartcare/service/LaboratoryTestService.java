package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.LaboratoryTest;
import com.smartcare.smartcare.repository.LaboratoryTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryTestService {

    private final LaboratoryTestRepository laboratoryTestRepository;

    public LaboratoryTestService(
            LaboratoryTestRepository laboratoryTestRepository) {
        this.laboratoryTestRepository = laboratoryTestRepository;
    }

    public List<LaboratoryTest> getAllTests() {
        return laboratoryTestRepository.findAll();
    }

    public Optional<LaboratoryTest> getTestById(Integer id) {
        return laboratoryTestRepository.findById(id);
    }

    public LaboratoryTest createTest(LaboratoryTest test) {
        return laboratoryTestRepository.save(test);
    }

    public LaboratoryTest updateTest(
            Integer id,
            LaboratoryTest details) {

        LaboratoryTest test = laboratoryTestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Laboratory test not found with id: " + id));

        test.setPatient(details.getPatient());
        test.setTestName(details.getTestName());
        test.setTestDate(details.getTestDate());
        test.setTestResult(details.getTestResult());
        test.setTechnicianName(details.getTechnicianName());
        test.setTestStatus(details.getTestStatus());

        return laboratoryTestRepository.save(test);
    }

    public void deleteTest(Integer id) {
        if (!laboratoryTestRepository.existsById(id)) {
            throw new RuntimeException(
                    "Laboratory test not found with id: " + id);
        }

        laboratoryTestRepository.deleteById(id);
    }
}