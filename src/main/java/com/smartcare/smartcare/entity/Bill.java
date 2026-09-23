package com.smartcare.smartcare.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer billId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "bill_date", nullable = false)
    private LocalDate billDate;

    @Column(name = "consultation_charges", nullable = false)
    private BigDecimal consultationCharges = BigDecimal.ZERO;

    @Column(name = "room_charges", nullable = false)
    private BigDecimal roomCharges = BigDecimal.ZERO;

    @Column(name = "laboratory_charges", nullable = false)
    private BigDecimal laboratoryCharges = BigDecimal.ZERO;

    @Column(name = "medicine_charges", nullable = false)
    private BigDecimal medicineCharges = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "payment_method")
    private String paymentMethod;

    public Bill() {
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getConsultationCharges() {
        return consultationCharges;
    }

    public void setConsultationCharges(BigDecimal consultationCharges) {
        this.consultationCharges = consultationCharges;
    }

    public BigDecimal getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(BigDecimal roomCharges) {
        this.roomCharges = roomCharges;
    }

    public BigDecimal getLaboratoryCharges() {
        return laboratoryCharges;
    }

    public void setLaboratoryCharges(BigDecimal laboratoryCharges) {
        this.laboratoryCharges = laboratoryCharges;
    }

    public BigDecimal getMedicineCharges() {
        return medicineCharges;
    }

    public void setMedicineCharges(BigDecimal medicineCharges) {
        this.medicineCharges = medicineCharges;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}