package com.unionsystems.ncscmb.model;

import lombok.Data;
import java.util.List;

@Data
public class IncomingPaymentData {
    private long id;
    private int version;
    private String paymentDate;
    private String customsCode;
    private String companyCode;
    private String declarantCode;
    private String bankCode;
    private String sadRegSerial;
    private String sadRegNumber;
    private String sadYear;
    private long amount;
    private List<Payments> payments;
}
