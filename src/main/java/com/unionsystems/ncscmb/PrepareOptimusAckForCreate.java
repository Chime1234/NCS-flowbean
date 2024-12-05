package com.unionsystems.ncscmb;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionsystems.ncscmb.model.AssessmentData;
import com.unionsystems.ncscmb.model.Acknowledgment;
import com.unionsystems.ncscmb.model.AcknowledgementHeader;
import com.unionsystems.ncscmb.model.OnlineDutyPaymentAcknowledgment;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
public class PrepareOptimusAckForCreate implements FlowBean {
    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        try {
            // Convert the body to AssessmentData
            String payload = exchange.getBody().toString();

            ObjectMapper objectMapper = new ObjectMapper();
            AssessmentData assessmentData = objectMapper.readValue(payload, AssessmentData.class);

            // Extract and parse "PAID_ASSESSMENT" header
            Map<String, Object> xchgHeader = exchange.getHeader();
            String paidAssessmentJson = (String) xchgHeader.get("PAID_ASSESSMENT");
            Map<String, Object> assessment = objectMapper.readValue(paidAssessmentJson, Map.class);

            // Get values from the assessment map
            String paarNo = (String) assessment.get("PAAR_NO");
            String billOfEntryNo = (String) assessment.get("BOE_NO");
            String debitAccount = (String) assessment.get("DR_ACCOUNT");
            String paymentDate = (String) assessment.get("PAYMENT_DATE");
            String transactionRef = (String) assessment.get("TXN_REF");
            String paymentReference = (String) assessment.get("REF");

            // Map to XML DTO
            Acknowledgment acknowledgment = new Acknowledgment();
            AcknowledgementHeader header = new AcknowledgementHeader();
            OnlineDutyPaymentAcknowledgment onlineDutyPayment = new OnlineDutyPaymentAcknowledgment();

            // Populate OnlineDutyPaymentAcknowledgment fields
            onlineDutyPayment.setAssessmentNo(assessmentData.getSadAssessmentNumber());
            onlineDutyPayment.setAssessmentSerial(assessmentData.getSadAssessmentSerial());
            onlineDutyPayment.setAssessmentYear(String.valueOf(assessmentData.getSadYear()));
            onlineDutyPayment.setCompanyName(assessmentData.getCompanyName());
            onlineDutyPayment.setCompanyCode(assessmentData.getCompanyCode());
            onlineDutyPayment.setCustomsArea(assessmentData.getCustomsCode());
            onlineDutyPayment.setFormMReference(assessmentData.getFormMNumber());
            onlineDutyPayment.setDeclarantCode(assessmentData.getDeclarantCode());
            onlineDutyPayment.setDeclarantName(assessmentData.getDeclarantName());
            onlineDutyPayment.setPartial(assessmentData.getVersion());
            onlineDutyPayment.setRegistrationDate(assessmentData.getSadAssessmentDate());
            onlineDutyPayment.setTotalAmount(assessmentData.getTotalAmountToBePaid());
            onlineDutyPayment.setPaarNo(paarNo);
            onlineDutyPayment.setBillOfEntryNo(billOfEntryNo);
            onlineDutyPayment.setDebitAccount(debitAccount);
            onlineDutyPayment.setPaymentDate(paymentDate);
            onlineDutyPayment.setPaymentReference(paymentReference);
            onlineDutyPayment.setAuthorityReference(transactionRef);
            onlineDutyPayment.setPaymentStatus(assessmentData.getStatus());
            onlineDutyPayment.setPaymentStatus(assessmentData.getStatus());

            acknowledgment.setAcknowledgementHeader(header);
            acknowledgment.setOnlineDutyPaymentAcknowledgment(onlineDutyPayment);

            // Convert to XML
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

            String xmlPayload = xmlMapper.writeValueAsString(acknowledgment);

            // Set XML as the response body
            exchange.setBody(xmlPayload);
        } catch (Exception e) {
            log.error("Error processing payload", e);
            throw e;
        }
        return exchange;
    }
}
