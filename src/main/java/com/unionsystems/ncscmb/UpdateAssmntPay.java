package com.unionsystems.ncscmb;


import com.unionsystems.ncscmb.model.AssessmentData;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import com.unionsystems.ncscmb.model.TransactionResponse;
import com.unionsystems.ncscmb.model.SadAsmt;
import com.unionsystems.ncscmb.model.Info;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UpdateAssmntPay implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        // Extract the incoming header values
        Map<String, Object> headers = exchange.getHeader();

        headers.put("PMT_RESP", "Operation completed successfully, SGD was paid");
        headers.put("NCS_PAID", Integer.valueOf(1));
        headers.put("ASSESSMENT_STATUS", "CONFIRMED");

        return exchange;
    }
}
