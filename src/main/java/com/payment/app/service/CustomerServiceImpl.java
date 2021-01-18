package com.payment.app.service;

import com.payment.app.dto.CustomerDto;
import com.payment.app.dto.PaymentDTO;
import com.payment.app.helper.User;
import com.payment.app.model.Customer;
import com.payment.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders header;

    @Override
    public String registerCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findByEmail(customerDto.getEmail());
        if(customer == null){

        }
    }

    private void createCustomer(Customer customer,CustomerDto customerDto){
        String Url = "https://api.test.paysafe.com/paymenthub/v1/customers";
        User customerJSON = new User();

        customerJSON.setEmail(customerDto.getEmail());
        customerJSON.setMerchantCustomerId(customerDto.getEmail()+ UUID.randomUUID());

        HttpEntity<User> entity = new HttpEntity<User>(customerJSON,header);
        ResponseCustomer responseCustomerJSON = (ResponseCustomer) restTemplate.postForObject(
                Url, entity, ResponseCustomer.class);

        customer.setEmail(customerDto.getEmail());
        customer.setCustomerId(responseCustomerJSON.getId());
        customerRepository.save(customer);
    }
    @Override
    public String completePayment(PaymentDTO paymentDTO) {
        return null;
    }
}
