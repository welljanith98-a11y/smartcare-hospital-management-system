package com.smartcare.smartcare.service;

import java.math.BigDecimal;

public class CashPayment implements PaymentService {

    @Override
    public String makePayment(BigDecimal amount) {
        return "Cash payment received: " + amount;
    }
}