package com.payment.app.dto;

public class PaymentDTO {
    private Long amount;
    private String email;
    private String paymentHandleToken;
    private String customerOperation;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentHandleToken() {
        return paymentHandleToken;
    }

    public void setPaymentHandleToken(String paymentHandleToken) {
        this.paymentHandleToken = paymentHandleToken;
    }

    public String getCustomerOperation() {
        return customerOperation;
    }

    public void setCustomerOperation(String customerOperation) {
        this.customerOperation = customerOperation;
    }
}
