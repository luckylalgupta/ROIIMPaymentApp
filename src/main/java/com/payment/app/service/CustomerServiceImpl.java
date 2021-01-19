package com.payment.app.service;

import com.payment.app.dto.CustomerDto;
import com.payment.app.dto.PaymentDto;
import com.payment.app.helper.PaymentJSON.Payment;
import com.payment.app.helper.PaymentJSON.ResponsePayment;
import com.payment.app.helper.ResponseCustomer;
import com.payment.app.helper.ResponseToken;
import com.payment.app.helper.Token;
import com.payment.app.helper.User;
import com.payment.app.model.Customer;
import com.payment.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
@Service
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
            createCustomer(new Customer(),customerDto);
        }
        String str = createSingleUseToken(customerRepository.findByEmail(customerDto.getEmail()));
        return str;
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
    private String createSingleUseToken(Customer customer){
        String Url = "https://api.test.paysafe.com/paymenthub/v1/customers/"+customer.getCustomerId()+"/singleusecustomertokens";
        Token tokenJSON = new Token();

        tokenJSON.setMerchantRefNum(""+UUID.randomUUID());

        HttpEntity<Token> entity = new HttpEntity<Token>(tokenJSON,header);
        ResponseToken responseTokenJSON = (ResponseToken) restTemplate.postForObject(
                Url,  entity, ResponseToken.class);

        return responseTokenJSON.getSingleUseCustomerToken();
    }
    @Override
    public String completePayment(PaymentDto paymentDTO) {
        String url ="https://api.test.paysafe.com/paymenthub/v1/payments";
        Payment paymentJSON = new Payment();
        if(paymentDTO.getCustomerOperation()!=null && paymentDTO.getCustomerOperation().equals("ADD")){
            Customer customer = customerRepository.findByEmail(paymentDTO.getEmail());
            customer.setCustomerOperation("ADD");
            paymentJSON.setCustomerId(customerRepository.findByEmail(paymentDTO.getEmail()).getCustomerId());
            customerRepository.save(customer);
        }
        paymentJSON.setPaymentHandleToken(paymentDTO.getPaymentHandleToken());
        paymentJSON.setAmount(paymentDTO.getAmount());
        paymentJSON.setMerchantRefNum(UUID.randomUUID()+"");
        HttpEntity<Payment> entity = new HttpEntity<Payment>(paymentJSON,header);
        try{
            ResponsePayment responsePaymentJSON =(ResponsePayment)restTemplate.postForObject(url,entity,ResponsePayment.class);
            if(responsePaymentJSON.getStatus().equals("COMPLETED")) return responsePaymentJSON.getStatus();

        }catch (HttpClientErrorException ex){
            if(ex.getStatusCode()!= HttpStatus.BAD_REQUEST){
                return null;
            }
        }
        return null;
    }
}
