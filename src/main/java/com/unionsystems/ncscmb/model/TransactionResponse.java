package com.unionsystems.ncscmb.model;

import lombok.Data;

@Data
public class TransactionResponse {
    private String version;
    private String CustomsCode;
    private String DeclarantCode;
    private SadAsmt SadAsmt;
    private String TransactionStatus;
    private Info Info;
}