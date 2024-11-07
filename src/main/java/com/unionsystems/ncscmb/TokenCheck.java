package com.unionsystems.ncscmb;

import com.unionsystems.ncscmb.util.Util;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionsystems.ncscmb.model.TokenResponse;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
public class TokenCheck implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        Map<String, Object> headers = exchange.getHeader();
        String authUrl = headers.get("AUTH_BASE_URL").toString();
        String baseUrl = headers.get("API_BASE_URL").toString();
        String path = headers.get("AUTH_PATH").toString();
        String expiryDateHeader = headers.get("EXPR_DATE").toString();
        String tokenHeader = headers.get("BEARER_TOKEN").toString();
        String creatDate = headers.get("CRT_DATE").toString();
        String incomingJson = headers.get("AUTH_CRED").toString();


        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime expiryDate = OffsetDateTime.parse(expiryDateHeader, formatter);

        OffsetDateTime now = OffsetDateTime.now();

        if (now.isBefore(expiryDate)) {
            log.info("Token is still valid.");
            headers.put("BEARER_TOKEN", tokenHeader);
            headers.put("EXPR_DATE", expiryDateHeader);
            headers.put("CRT_DATE", creatDate);
        } else {
            log.warn("Token has expired.");

            String tokencall = Util.getTokenCall(authUrl, path, incomingJson);

            ObjectMapper objectMapper = new ObjectMapper();
            TokenResponse tokenResponse = objectMapper.readValue(tokencall, TokenResponse.class);

            String newToken = tokenResponse.getToken();
            String newExpiryDate = tokenResponse.getExpiryDate();
            String createdDate = tokenResponse.getRefreshToken().getCreated();

            log.info("New token: " + newToken);
            log.info("New expiry date: " + newExpiryDate);
            log.info("Token created on: " + createdDate);

            headers.put("BEARER_TOKEN", newToken);
            headers.put("EXPR_DATE", newExpiryDate);
            headers.put("CRT_DATE", createdDate);
        }
        return exchange;
    }
}
