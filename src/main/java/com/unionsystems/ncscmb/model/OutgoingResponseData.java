package com.unionsystems.ncscmb.model;

import lombok.Data;

@Data
public class OutgoingResponseData {
    private String year;
    private String assessmentSerial;
    private String assessmentVersion;
    private String customsCode;
    private String assessmentNumber;
    private String dateVerified;
    private String status;
}