package com.smartcare.smartcare.controller;

import com.smartcare.smartcare.entity.Bill;
import com.smartcare.smartcare.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(
            @PathVariable Integer id) {

        return billService.getBillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(
            @RequestBody Bill bill) {

        return ResponseEntity.ok(
                billService.createBill(bill)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(
            @PathVariable Integer id,
            @RequestBody Bill bill) {

        return ResponseEntity.ok(
                billService.updateBill(id, bill)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(
            @PathVariable Integer id) {

        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
