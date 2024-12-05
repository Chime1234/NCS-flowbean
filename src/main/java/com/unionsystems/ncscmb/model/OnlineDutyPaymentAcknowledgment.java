package com.unionsystems.ncscmb.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class OnlineDutyPaymentAcknowledgment {
    @JacksonXmlProperty(localName = "AssessmentType")
    private String assessmentType = "IMPORT_DUTY";

    @JacksonXmlProperty(localName = "AssessmentNo")
    private String assessmentNo;

    @JacksonXmlProperty(localName = "AssessmentSerial")
    private String assessmentSerial;

    @JacksonXmlProperty(localName = "AssessmentYear")
    private String assessmentYear;

    @JacksonXmlProperty(localName = "CompanyName")
    private String companyName;

    @JacksonXmlProperty(localName = "CompanyCode")
    private String companyCode;

    @JacksonXmlProperty(localName = "CustomsArea")
    private String customsArea;

    @JacksonXmlProperty(localName = "FormMReference")
    private String formMReference;

    @JacksonXmlProperty(localName = "DeclarantCode")
    private String declarantCode;

    @JacksonXmlProperty(localName = "DeclarantName")
    private String declarantName;

    @JacksonXmlProperty(localName = "Partial")
    private int partial;

    @JacksonXmlProperty(localName = "RegistrationDate")
    private String registrationDate;

    @JacksonXmlProperty(localName = "TotalAmount")
    private long totalAmount;

    @JacksonXmlProperty(localName = "PaarNo")
    private String paarNo;

    @JacksonXmlProperty(localName = "BillOfEntryNo")
    private String billOfEntryNo;

    @JacksonXmlProperty(localName = "DebitAccount")
    private String debitAccount;

    @JacksonXmlProperty(localName = "PaymentDate")
    private String paymentDate;

    @JacksonXmlProperty(localName = "PaymentReference")
    private String paymentReference;

    @JacksonXmlProperty(localName = "AuthorityReference")
    private String authorityReference;

    @JacksonXmlProperty(localName = "PaymentStatus")
    private String paymentStatus;

}
