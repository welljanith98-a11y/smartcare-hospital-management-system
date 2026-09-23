package com.smartcare.smartcare.service;

import com.smartcare.smartcare.entity.Bill;
import com.smartcare.smartcare.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBillById(Integer id) {
        return billRepository.findById(id);
    }

    public Bill createBill(Bill bill) {

        validateCharges(bill);

        if (bill.getBillDate() == null) {
            bill.setBillDate(LocalDate.now());
        }

        if (bill.getPaymentStatus() == null ||
                bill.getPaymentStatus().isBlank()) {
            bill.setPaymentStatus("PENDING");
        }

        setNullChargesToZero(bill);

        BigDecimal total =
                bill.getConsultationCharges()
                        .add(bill.getRoomCharges())
                        .add(bill.getLaboratoryCharges())
                        .add(bill.getMedicineCharges());

        bill.setTotalAmount(total);

        return billRepository.save(bill);
    }

    public Bill updateBill(Integer id, Bill details) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bill not found with id: " + id));

        setNullChargesToZero(details);

        validateCharges(details);

        if (details.getBillDate() == null) {
            details.setBillDate(LocalDate.now());
        }

        if (details.getPaymentStatus() == null ||
                details.getPaymentStatus().isBlank()) {
            details.setPaymentStatus("PENDING");
        }

        bill.setPatient(details.getPatient());
        bill.setBillDate(details.getBillDate());
        bill.setConsultationCharges(details.getConsultationCharges());
        bill.setRoomCharges(details.getRoomCharges());
        bill.setLaboratoryCharges(details.getLaboratoryCharges());
        bill.setMedicineCharges(details.getMedicineCharges());
        bill.setPaymentStatus(details.getPaymentStatus());
        bill.setPaymentMethod(details.getPaymentMethod());

        BigDecimal total =
                details.getConsultationCharges()
                        .add(details.getRoomCharges())
                        .add(details.getLaboratoryCharges())
                        .add(details.getMedicineCharges());

        bill.setTotalAmount(total);

        return billRepository.save(bill);
    }

    public void deleteBill(Integer id) {

        if (!billRepository.existsById(id)) {
            throw new RuntimeException(
                    "Bill not found with id: " + id);
        }

        billRepository.deleteById(id);
    }

    private void setNullChargesToZero(Bill bill) {

        if (bill.getConsultationCharges() == null) {
            bill.setConsultationCharges(BigDecimal.ZERO);
        }

        if (bill.getRoomCharges() == null) {
            bill.setRoomCharges(BigDecimal.ZERO);
        }

        if (bill.getLaboratoryCharges() == null) {
            bill.setLaboratoryCharges(BigDecimal.ZERO);
        }

        if (bill.getMedicineCharges() == null) {
            bill.setMedicineCharges(BigDecimal.ZERO);
        }
    }

    private void validateCharges(Bill bill) {

        if (bill.getConsultationCharges() != null &&
                bill.getConsultationCharges()
                        .compareTo(BigDecimal.ZERO) < 0) {

            throw new RuntimeException(
                    "Bill charges cannot be negative");
        }

        if (bill.getRoomCharges() != null &&
                bill.getRoomCharges()
                        .compareTo(BigDecimal.ZERO) < 0) {

            throw new RuntimeException(
                    "Bill charges cannot be negative");
        }

        if (bill.getLaboratoryCharges() != null &&
                bill.getLaboratoryCharges()
                        .compareTo(BigDecimal.ZERO) < 0) {

            throw new RuntimeException(
                    "Bill charges cannot be negative");
        }

        if (bill.getMedicineCharges() != null &&
                bill.getMedicineCharges()
                        .compareTo(BigDecimal.ZERO) < 0) {

            throw new RuntimeException(
                    "Bill charges cannot be negative");
        }
    }
}