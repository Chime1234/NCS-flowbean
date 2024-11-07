package com.unionsystems.ncscmb;


import com.unionsystems.ncscmb.model.AssRoot;
import com.unionsystems.ncscmb.model.AssessmentData;
import com.unionsystems.ncscmb.model.Taxes;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonAssRes implements FlowBean {
    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        String payload = exchange.getBody().toString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        AssRoot assRoot = mapper.readValue(payload, AssRoot.class);
        log.info(assRoot.toString());
        exchange.setBody(assRoot.getData());
        return exchange;
    }
}