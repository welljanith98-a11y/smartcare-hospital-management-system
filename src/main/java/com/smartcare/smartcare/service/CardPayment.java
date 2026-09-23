package com.smartcare.smartcare.service;

import java.math.BigDecimal;

public class CardPayment implements PaymentService {

    @Override
    public String makePayment(BigDecimal amount) {
        return "Card payment received: " + amount;
    }
}