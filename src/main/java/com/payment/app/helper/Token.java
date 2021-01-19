package com.payment.app.helper;

public class Token {
    private String merchantRefNum;

    private String[] paymentTypes = {"CARD"};

    public String getMerchantRefNum() {
        return merchantRefNum;
    }

    public void setMerchantRefNum(String merchantRefNum) {
        this.merchantRefNum = merchantRefNum;
    }

    public String[] getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(String[] paymentTypes) {
        this.paymentTypes = paymentTypes;
    }
}
