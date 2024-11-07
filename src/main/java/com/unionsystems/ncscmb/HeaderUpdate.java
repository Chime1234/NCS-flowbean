package com.unionsystems.ncscmb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;

import java.util.Map;

public class HeaderUpdate implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        String payload = exchange.getBody().toString();
        Map<String, Object> headers = exchange.getHeader();
        headers.put("INCOMING_BODY", payload);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(payload);

        // Extract fields from the incoming JSON
        String sadYear = rootNode.path("sadYear").asText();
        String customsCode = rootNode.path("customsCode").asText();
        String sadRegSerial = rootNode.path("sadRegSerial").asText();
        String sadRegNumber = rootNode.path("sadRegNumber").asText();
        int version = rootNode.path("version").asInt();
        double amount = rootNode.path("payments").get(0).path("amount").asDouble();
        String id = rootNode.path("id").asText();
        headers.put("ID", id);
        return exchange;
    }
}
