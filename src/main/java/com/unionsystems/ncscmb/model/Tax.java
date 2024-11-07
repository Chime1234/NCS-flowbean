package com.unionsystems.ncscmb.model;

import lombok.Data;

@Data
public class Tax {
    private String taxCode;

    private long taxAmount;
}