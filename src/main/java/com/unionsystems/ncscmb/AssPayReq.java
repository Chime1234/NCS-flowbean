package com.unionsystems.ncscmb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
public class AssPayReq implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        try {
            Map<String, Object> headers = exchange.getHeader();
            String baseUrl = headers.get("API_BASE_URL").toString();
            String path = headers.get("POST_PATH").toString();
            String token = headers.get("BEARER_TOKEN").toString();

            String incomingJson = exchange.getBody().toString();

            String regJson  = incomingJson.replaceAll("\\r?\\n", "");

            log.info("Remove line breaks" + regJson);

            headers.put("INCOMING_BODY", regJson);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(regJson);

            // Extract fields from the incoming JSON
            String sadYear = rootNode.path("sadYear").asText();
            String customsCode = rootNode.path("customsCode").asText();
            String sadRegSerial = rootNode.path("sadRegSerial").asText();
            String sadRegNumber = rootNode.path("sadRegNumber").asText();
            int version = rootNode.path("version").asInt();
            double amount = rootNode.path("payments").get(0).path("amount").asDouble();
            String id = rootNode.path("id").asText();


            String assessmentNoticeReferenceNumber = sadYear + customsCode + sadRegSerial + sadRegNumber + version;

            // Get the current date for receiptDate
            String receiptDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date());

            // Create the new JSON for the POST request
            ObjectNode newJson = objectMapper.createObjectNode();
            newJson.put("receiptDate", receiptDate);
            newJson.put("receiptNumber", "string");
            newJson.put("assessmentNoticeReferenceNumber", assessmentNoticeReferenceNumber);
            newJson.put("amountCollected", amount);

            // Convert to JSON string
            String jsonString = objectMapper.writeValueAsString(newJson);

            // Log the new JSON for verification
            log.info("Constructed JSON: " + jsonString);

            // Start of Fix - SSL/TLS Configuration
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            // End of Fix

            // Construct the full URL
            StringBuilder str = new StringBuilder();
            str.append(baseUrl).append(path);
            log.info("Constructed URL: " + str.toString());

            URL url = new URL(str.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setRequestProperty("Accept", "application/json");

            // Send the POST request with the JSON payload
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response
            InputStream inputStream;
            try {
                inputStream = con.getInputStream();
            } catch (IOException e) {
                inputStream = con.getErrorStream();
            }
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }
            String responseBody = response.toString();
            log.info("Response body: " + responseBody);

            // Set the response and the original incoming body in headers
            headers.put("HTTP_RESPONSE_BODY", responseBody);
            headers.put("ID", id);
            exchange.setBody(responseBody);

            return exchange;
        } catch (Exception e) {
            log.error("Error in processing the exchange", e);
            throw e;
        }
    }
}
