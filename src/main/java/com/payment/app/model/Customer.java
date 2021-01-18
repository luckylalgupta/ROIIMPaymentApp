package com.payment.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    private String email;

    private String customerId;

    private String customerOperation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerOperation() {
        return customerOperation;
    }

    public void setCustomerOperation(String customerOperation) {
        this.customerOperation = customerOperation;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerOperation='" + customerOperation + '\'' +
                '}';
    }
}
