package com.unionsystems.ncscmb;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.unionsystems.ncscmb.model.ServiceRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.unionsystems.ncscmb.model.AssessmentData;
import lombok.extern.slf4j.Slf4j;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;

import java.util.stream.Collectors;

@Slf4j
public class PrepOptForCrt implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        // Extract the JSON payload from the exchange body
        String payload = exchange.getBody().toString();

        // Deserialize JSON into AssessmentData DTO
        ObjectMapper objectMapper = new ObjectMapper();
        AssessmentData assessmentData = objectMapper.readValue(payload, AssessmentData.class);

        // Map AssessmentData to ServiceRequest
        ServiceRequest serviceRequest = new ServiceRequest();

        // Map RequestHeader
        ServiceRequest.RequestHeader header = new ServiceRequest.RequestHeader();
        header.setTransactionId(java.util.UUID.randomUUID().toString());
        ServiceRequest.RequestHeader.Credentials credentials = new ServiceRequest.RequestHeader.Credentials();
        credentials.setBankCode(assessmentData.getBankCode());
        credentials.setTin(assessmentData.getCompanyCode());
        header.setCredentials(credentials);
        serviceRequest.setRequestHeader(header);

        // Map OnlineDutyRequest
        ServiceRequest.OnlineDutyRequest onlineDutyRequest = new ServiceRequest.OnlineDutyRequest();
        onlineDutyRequest.setAssessmentNo(assessmentData.getSadAssessmentNumber());
        onlineDutyRequest.setAssessmentSerial(assessmentData.getSadAssessmentSerial());
        onlineDutyRequest.setAssessmentYear(assessmentData.getSadYear());
        onlineDutyRequest.setCompanyCode(assessmentData.getCompanyCode());
        onlineDutyRequest.setCompanyName(assessmentData.getCompanyName());
        onlineDutyRequest.setCustomsArea(assessmentData.getCustomsCode());
        onlineDutyRequest.setDeclarantCode(assessmentData.getDeclarantCode());
        onlineDutyRequest.setDeclarantName(assessmentData.getDeclarantName());
        onlineDutyRequest.setFormMReference(assessmentData.getAssessmentNoticeReferenceNumber());
        onlineDutyRequest.setRegistrationDate(assessmentData.getSadAssessmentDate());
        onlineDutyRequest.setTotalAmount(assessmentData.getTotalAmountToBePaid());

        // Map Taxes
        onlineDutyRequest.setTaxes(assessmentData.getTaxes().getTax().stream().map(tax -> {
            ServiceRequest.OnlineDutyRequest.Tax mappedTax = new ServiceRequest.OnlineDutyRequest.Tax();
            mappedTax.setCode(tax.getTaxCode());
            mappedTax.setAmount(tax.getTaxAmount());
            return mappedTax;
        }).collect(Collectors.toList()));

        serviceRequest.setOnlineDutyRequest(onlineDutyRequest);

        // Convert ServiceRequest DTO to XML using XmlMapper
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(serviceRequest);

        // Log the generated XML for debugging purposes
        log.info("Generated XML: \n{}", xmlOutput);

        // Update the exchange body with the XML output
        exchange.setBody(xmlOutput);
        return exchange;
    }
}
