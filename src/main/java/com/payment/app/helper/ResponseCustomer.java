package com.payment.app.helper;

public class ResponseCustomer {
    private String id;

    private String merchantCustomerId;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantCustomerId() {
        return merchantCustomerId;
    }

    public void setMerchantCustomerId(String merchantCustomerId) {
        this.merchantCustomerId = merchantCustomerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
