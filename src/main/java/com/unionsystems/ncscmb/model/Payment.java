package com.unionsystems.ncscmb.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Payment")
public class Payment {

    @JacksonXmlProperty(localName = "MeansOfPayment")
    private String meansOfPayment;

    @JacksonXmlProperty(localName = "Reference")
    private String reference;

    @JacksonXmlProperty(localName = "Amount")
    private String amount;
}