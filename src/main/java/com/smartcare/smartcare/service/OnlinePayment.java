package com.smartcare.smartcare.service;

import java.math.BigDecimal;

public class OnlinePayment implements PaymentService {

    @Override
    public String makePayment(BigDecimal amount) {
        return "Online payment received: " + amount;
    }
}