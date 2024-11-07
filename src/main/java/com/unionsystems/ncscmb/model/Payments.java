package com.unionsystems.ncscmb.model;

import lombok.Data;

@Data
public class Payments {
    private String meansOfPayment;
    private String reference;
    private long amount;
}