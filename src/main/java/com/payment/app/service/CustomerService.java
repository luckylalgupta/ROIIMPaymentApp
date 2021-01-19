package com.payment.app.service;

import com.payment.app.dto.CustomerDto;
import com.payment.app.dto.PaymentDto;

public interface CustomerService {
    public String registerCustomer(CustomerDto customerDto);

    public String completePayment(PaymentDto paymentDTO);
}
