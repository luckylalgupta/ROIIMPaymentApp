package com.payment.app.controller;

import com.payment.app.dto.PaymentDto;
import com.payment.app.dto.ResponseDto;
import com.payment.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
public class PaymentController {

    @Autowired
    CustomerService customerService;


    @PostMapping("/payment")
    public ResponseDto<String> payment(@Valid @RequestBody PaymentDto paymentDto){

        String status = customerService.completePayment(paymentDto);
        return new ResponseDto<>(
                HttpStatus.OK,
                status
        );
    }
}
