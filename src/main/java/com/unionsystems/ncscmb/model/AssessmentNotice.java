package com.unionsystems.ncscmb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class AssessmentNotice {
    private static final long serialVersionUID = -1091114342864164728L;

    @JsonProperty("assessmentNoticeReferenceNumber")
    private String assessmentNoticeReferenceNumber;

    @JsonProperty("version")
    private String version;

    @JsonProperty("customsCode")
    private String customsCode;

    @JsonProperty("declarantCode")
    private String declarantCode;

    @JsonProperty("declarantName")
    private String declarantName;

    @JsonProperty("sadYear")
    private String sadYear;

    @JsonProperty("sadAssessmentSerial")
    private String sadAssessmentSerial;

    @JsonProperty("sadAssessmentNumber")
    private String sadAssessmentNumber;

    @JsonProperty("sadAssessmentDate")
    private String sadAssessemntDate;

    @JsonProperty("companyCode")
    private String companyCode;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("bankCode")
    private String bankCode;

    @JsonProperty("bankBranchCode")
    private String bankBranchCode;

    @JsonProperty("formMNumber")
    private String formMNumber;

    @JsonProperty("taxes")
    private Taxes taxes;

    @JsonProperty("totalAmountToBePaid")
    private BigDecimal totalAmountToBePaid;

    @JsonProperty("receiptNumber")
    private String receiptNumber;

    @JsonProperty("status")
    private String status;

    @JsonProperty("assessmentNoticeReferenceNumber")
    public void setAssessmentNoticeReferenceNumber(String assessmentNoticeReferenceNumber) {
        this.assessmentNoticeReferenceNumber = assessmentNoticeReferenceNumber;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("customsCode")
    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    @JsonProperty("declarantCode")
    public void setDeclarantCode(String declarantCode) {
        this.declarantCode = declarantCode;
    }

    @JsonProperty("declarantName")
    public void setDeclarantName(String declarantName) {
        this.declarantName = declarantName;
    }

    @JsonProperty("sadYear")
    public void setSadYear(String sadYear) {
        this.sadYear = sadYear;
    }

    @JsonProperty("sadAssessmentSerial")
    public void setSadAssessmentSerial(String sadAssessmentSerial) {
        this.sadAssessmentSerial = sadAssessmentSerial;
    }

    @JsonProperty("sadAssessmentNumber")
    public void setSadAssessmentNumber(String sadAssessmentNumber) {
        this.sadAssessmentNumber = sadAssessmentNumber;
    }

    @JsonProperty("sadAssessmentDate")
    public void setSadAssessemntDate(String sadAssessemntDate) {
        this.sadAssessemntDate = sadAssessemntDate;
    }

    @JsonProperty("companyCode")
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("bankCode")
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @JsonProperty("bankBranchCode")
    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    @JsonProperty("formMNumber")
    public void setFormMNumber(String formMNumber) {
        this.formMNumber = formMNumber;
    }

    @JsonProperty("taxes")
    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    @JsonProperty("totalAmountToBePaid")
    public void setTotalAmountToBePaid(BigDecimal totalAmountToBePaid) {
        this.totalAmountToBePaid = totalAmountToBePaid;
    }

    @JsonProperty("receiptNumber")
    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AssessmentNotice))
            return false;
        AssessmentNotice other = (AssessmentNotice)o;
        if (!other.canEqual(this))
            return false;
        Object this$assessmentNoticeReferenceNumber = getAssessmentNoticeReferenceNumber(), other$assessmentNoticeReferenceNumber = other.getAssessmentNoticeReferenceNumber();
        if ((this$assessmentNoticeReferenceNumber == null) ? (other$assessmentNoticeReferenceNumber != null) : !this$assessmentNoticeReferenceNumber.equals(other$assessmentNoticeReferenceNumber))
            return false;
        Object this$version = getVersion(), other$version = other.getVersion();
        if ((this$version == null) ? (other$version != null) : !this$version.equals(other$version))
            return false;
        Object this$customsCode = getCustomsCode(), other$customsCode = other.getCustomsCode();
        if ((this$customsCode == null) ? (other$customsCode != null) : !this$customsCode.equals(other$customsCode))
            return false;
        Object this$declarantCode = getDeclarantCode(), other$declarantCode = other.getDeclarantCode();
        if ((this$declarantCode == null) ? (other$declarantCode != null) : !this$declarantCode.equals(other$declarantCode))
            return false;
        Object this$declarantName = getDeclarantName(), other$declarantName = other.getDeclarantName();
        if ((this$declarantName == null) ? (other$declarantName != null) : !this$declarantName.equals(other$declarantName))
            return false;
        Object this$sadYear = getSadYear(), other$sadYear = other.getSadYear();
        if ((this$sadYear == null) ? (other$sadYear != null) : !this$sadYear.equals(other$sadYear))
            return false;
        Object this$sadAssessmentSerial = getSadAssessmentSerial(), other$sadAssessmentSerial = other.getSadAssessmentSerial();
        if ((this$sadAssessmentSerial == null) ? (other$sadAssessmentSerial != null) : !this$sadAssessmentSerial.equals(other$sadAssessmentSerial))
            return false;
        Object this$sadAssessmentNumber = getSadAssessmentNumber(), other$sadAssessmentNumber = other.getSadAssessmentNumber();
        if ((this$sadAssessmentNumber == null) ? (other$sadAssessmentNumber != null) : !this$sadAssessmentNumber.equals(other$sadAssessmentNumber))
            return false;
        Object this$sadAssessemntDate = getSadAssessemntDate(), other$sadAssessemntDate = other.getSadAssessemntDate();
        if ((this$sadAssessemntDate == null) ? (other$sadAssessemntDate != null) : !this$sadAssessemntDate.equals(other$sadAssessemntDate))
            return false;
        Object this$companyCode = getCompanyCode(), other$companyCode = other.getCompanyCode();
        if ((this$companyCode == null) ? (other$companyCode != null) : !this$companyCode.equals(other$companyCode))
            return false;
        Object this$companyName = getCompanyName(), other$companyName = other.getCompanyName();
        if ((this$companyName == null) ? (other$companyName != null) : !this$companyName.equals(other$companyName))
            return false;
        Object this$bankCode = getBankCode(), other$bankCode = other.getBankCode();
        if ((this$bankCode == null) ? (other$bankCode != null) : !this$bankCode.equals(other$bankCode))
            return false;
        Object this$bankBranchCode = getBankBranchCode(), other$bankBranchCode = other.getBankBranchCode();
        if ((this$bankBranchCode == null) ? (other$bankBranchCode != null) : !this$bankBranchCode.equals(other$bankBranchCode))
            return false;
        Object this$formMNumber = getFormMNumber(), other$formMNumber = other.getFormMNumber();
        if ((this$formMNumber == null) ? (other$formMNumber != null) : !this$formMNumber.equals(other$formMNumber))
            return false;
        Object this$taxes = getTaxes(), other$taxes = other.getTaxes();
        if ((this$taxes == null) ? (other$taxes != null) : !this$taxes.equals(other$taxes))
            return false;
        Object this$totalAmountToBePaid = getTotalAmountToBePaid(), other$totalAmountToBePaid = other.getTotalAmountToBePaid();
        if ((this$totalAmountToBePaid == null) ? (other$totalAmountToBePaid != null) : !this$totalAmountToBePaid.equals(other$totalAmountToBePaid))
            return false;
        Object this$receiptNumber = getReceiptNumber(), other$receiptNumber = other.getReceiptNumber();
        if ((this$receiptNumber == null) ? (other$receiptNumber != null) : !this$receiptNumber.equals(other$receiptNumber))
            return false;
        Object this$status = getStatus(), other$status = other.getStatus();
        return !((this$status == null) ? (other$status != null) : !this$status.equals(other$status));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AssessmentNotice;
    }

    public String toString() {
        return "AssessmentNotice(assessmentNoticeReferenceNumber=" + getAssessmentNoticeReferenceNumber() + ", version=" + getVersion() + ", customsCode=" + getCustomsCode() + ", declarantCode=" + getDeclarantCode() + ", declarantName=" + getDeclarantName() + ", sadYear=" + getSadYear() + ", sadAssessmentSerial=" + getSadAssessmentSerial() + ", sadAssessmentNumber=" + getSadAssessmentNumber() + ", sadAssessemntDate=" + getSadAssessemntDate() + ", companyCode=" + getCompanyCode() + ", companyName=" + getCompanyName() + ", bankCode=" + getBankCode() + ", bankBranchCode=" + getBankBranchCode() + ", formMNumber=" + getFormMNumber() + ", taxes=" + getTaxes() + ", totalAmountToBePaid=" + getTotalAmountToBePaid() + ", receiptNumber=" + getReceiptNumber() + ", status=" + getStatus() + ")";
    }

    public String getAssessmentNoticeReferenceNumber() {
        return this.assessmentNoticeReferenceNumber;
    }

    public String getVersion() {
        return this.version;
    }

    public String getCustomsCode() {
        return this.customsCode;
    }

    public String getDeclarantCode() {
        return this.declarantCode;
    }

    public String getDeclarantName() {
        return this.declarantName;
    }

    public String getSadYear() {
        return this.sadYear;
    }

    public String getSadAssessmentSerial() {
        return this.sadAssessmentSerial;
    }

    public String getSadAssessmentNumber() {
        return this.sadAssessmentNumber;
    }

    public String getSadAssessemntDate() {
        return this.sadAssessemntDate;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public String getBankBranchCode() {
        return this.bankBranchCode;
    }

    public String getFormMNumber() {
        return this.formMNumber;
    }

    public Taxes getTaxes() {
        return this.taxes;
    }

    public BigDecimal getTotalAmountToBePaid() {
        return this.totalAmountToBePaid;
    }

    public String getReceiptNumber() {
        return this.receiptNumber;
    }

    public String getStatus() {
        return this.status;
    }
}

