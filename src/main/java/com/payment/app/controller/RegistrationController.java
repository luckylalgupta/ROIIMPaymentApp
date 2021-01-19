package com.payment.app.controller;

import com.payment.app.dto.CustomerDto;
import com.payment.app.dto.ResponseDto;
import com.payment.app.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
public class RegistrationController {
    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/customer/register")
    public ResponseDto<String> registerCustomer (@Valid @RequestBody CustomerDto customerDto){
        String singleUseCustomerToken = customerService.registerCustomer(customerDto);
        System.out.println(singleUseCustomerToken);

        return new ResponseDto<T>(
                HttpStatus.OK,
                singleUseCustomerToken
        );
    }
}
