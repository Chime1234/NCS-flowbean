package com.unionsystems.ncscmb.model;
import lombok.Data;

@Data
public class AssessmentData {
    private String assessmentNoticeReferenceNumber;

    private int version;

    private int sadYear;

    private String customsCode;

    private String declarantCode;

    private String declarantName;

    private String sadAssessmentSerial;

    private String sadAssessmentNumber;

    private String sadAssessmentDate;

    private String companyCode;

    private String companyName;

    private String bankCode;

    private String bankBranchCode;

    private String formMNumber;

    private Taxes taxes;

    private long totalAmountToBePaid;

    private String receiptNumber;

    private String status;
}
