package com.unionsystems.ncscmb.model;

import lombok.Data;

@Data
public class OutgoingResponse {
    private String product;
    private String correlationId;
    private OutgoingResponseData data;
}