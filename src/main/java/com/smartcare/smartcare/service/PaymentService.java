package com.smartcare.smartcare.service;

import java.math.BigDecimal;

public interface PaymentService {

    String makePayment(BigDecimal amount);
}