package com.unionsystems.ncscmb.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "eAssessmentNotice")
public class EAssessmentNotice {

    @JacksonXmlProperty(isAttribute = true, localName = "version")
    private String version;

    @JacksonXmlProperty(localName = "SADYear")
    private String sadYear;

    @JacksonXmlProperty(localName = "CustomsCode")
    private String customsCode;

    @JacksonXmlProperty(localName = "DeclarantCode")
    private String declarantCode;

    @JacksonXmlProperty(localName = "DeclarantName")
    private String declarantName;

    @JacksonXmlProperty(localName = "SADAssessmentSerial")
    private String sadAssessmentSerial;

    @JacksonXmlProperty(localName = "SADAssessmentNumber")
    private String sadAssessmentNumber;

    @JacksonXmlProperty(localName = "SADAssessmentDate")
    private String sadAssessmentDate;

    @JacksonXmlProperty(localName = "CompanyCode")
    private String companyCode;

    @JacksonXmlProperty(localName = "CompanyName")
    private String companyName;

    @JacksonXmlProperty(localName = "BankCode")
    private String bankCode;

    @JacksonXmlProperty(localName = "BankBranchCode")
    private String bankBranchCode;

    @JacksonXmlProperty(localName = "FormMNumber")
    private String formMNumber;

    @JacksonXmlProperty(localName = "Taxes")
    private Taxes taxes;

    @JacksonXmlProperty(localName = "TotalAmountToBePaid")
    private String totalAmountToBePaid;
}