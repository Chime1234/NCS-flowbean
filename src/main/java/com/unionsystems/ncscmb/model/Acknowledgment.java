package com.unionsystems.ncscmb.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Acknowledgment {
    @JacksonXmlProperty(localName = "AcknowledgementHeader")
    private AcknowledgementHeader acknowledgementHeader;

    @JacksonXmlProperty(localName = "OnlineDutyPaymentAcknowledgment")
    private OnlineDutyPaymentAcknowledgment onlineDutyPaymentAcknowledgment;
}
