package com.unionsystems.ncscmb.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExciseAssessmentNotice {

    private String year;

    private String customscode;

    private String companycode;

    private String assessmentSerial;

    private String assessmentNumber;

    private String assessmentDate;

    private String bankCode;

    private String bankBranchCode;

    private Taxes taxes;

    private BigDecimal totalAmountToBePaid;

    private String version;
}
