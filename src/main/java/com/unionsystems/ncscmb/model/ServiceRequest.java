package com.unionsystems.ncscmb.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class ServiceRequest {
    private RequestHeader requestHeader;
    private OnlineDutyRequest onlineDutyRequest;

    @Data
    public static class RequestHeader {
        private String module = "customsduty";
        private String product = "onlineduty";
        private Credentials credentials;
        private String targetSystem = "OPTIMUS";
        private String sourceSystem = "TRADEX";
        private String transactionId;
        private String eventType = "F";
        private String event = "CRE";

        @Data
        public static class Credentials {
            private String bankCode;
            private String tin;
        }
    }

    @Data
    public static class OnlineDutyRequest {
        private String assessmentNo;
        private String assessmentSerial;
        private String assessmentType = "IMPORT DUTY";
        private int assessmentYear;
        private String companyCode;
        private String companyName;
        private String customsArea;
        private String declarantCode;
        private String declarantName;
        private String formMReference;
        private int partial = 0;
        private String registrationDate;
        private double totalAmount;
        private String currency = "NGN";

        @JacksonXmlElementWrapper(localName = "taxList")
        @JacksonXmlProperty(localName = "tax")
        private List<Tax> taxes;

        @Data
        public static class Tax {
            private String code;
            private String currency = "NGN";
            private double amount;
        }
    }
}
