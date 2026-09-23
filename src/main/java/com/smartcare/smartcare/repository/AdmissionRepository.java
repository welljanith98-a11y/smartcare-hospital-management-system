package com.smartcare.smartcare.repository;

import com.smartcare.smartcare.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {
}