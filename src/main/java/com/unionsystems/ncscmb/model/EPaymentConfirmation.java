package com.unionsystems.ncscmb.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ePaymentConfirmation")
public class EPaymentConfirmation {

    @JacksonXmlProperty(isAttribute = true, localName = "version")
    private String version;

    @JacksonXmlProperty(localName = "Document")
    private String document;

    @JacksonXmlProperty(localName = "PaymentDate")
    private String paymentDate;

    @JacksonXmlProperty(localName = "CustomsCode")
    private String customsCode;

    @JacksonXmlProperty(localName = "DeclarantCode")
    private String declarantCode;

    @JacksonXmlProperty(localName = "BankCode")
    private String bankCode;

    @JacksonXmlProperty(localName = "SadAsmt")
    private SadAsmt sadAsmt;

    @JacksonXmlProperty(localName = "Payment")
    private Payment payment;

    @JacksonXmlProperty(localName = "TotalAmountToBePaid")
    private String totalAmountToBePaid;
}