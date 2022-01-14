package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCard {

    private String creditCardNumber;
    private String issuingCompany;
    private Integer creditScore;

}
