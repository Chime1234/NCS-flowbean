package com.unionsystems.ncscmb;


import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.Map;

@Slf4j
public class UpdateGetAssHeader implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        String payload = exchange.getBody().toString();

        JSONObject jsonObject = new JSONObject(payload);
        log.info(">>>>>>>>> incoming body ======>>>>>> " + jsonObject);

        // Get headers from the exchange
        Map<String, Object> excheaders = exchange.getHeader();

        // Extract the sadAssessmentSerial field
        String sadAssessmentSerial = jsonObject.getString("sadAssessmentSerial");
        log.info("the serial is === " + sadAssessmentSerial);

        // Set the header based on the sadAssessmentSerial value
        if ("A".equals(sadAssessmentSerial)) {
            excheaders.put("ROUTE_NAME", "eAssessmentNotice");
        }
        if ("Q".equals(sadAssessmentSerial)) {
            excheaders.put("ROUTE_NAME", "eExciseAssessmentNotice");
        }

        // Update the headers in the exchange
        exchange.setHeader(excheaders);

        return exchange;
    }
}
