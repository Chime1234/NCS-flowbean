package com.unionsystems.ncscmb;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Map;

@Slf4j
public class  PrepareGetAssessments implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {

        try {
            Map<String, Object> headers = exchange.getHeader();
            String baseUrl = headers.get("API_BASE_URL").toString();
            String path = headers.get("GET_ASS_PATH").toString();
            String token = headers.get("BEARER_TOKEN").toString();
//          String assref =  headers.get("NCS_ASSREF").toString();

            log.info("==========log path" + path);


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

//  https://demo.ucms.ng/back/api/SgdDocument/GetFullAssessmentNotices?PageNumber=0&PageSize=0

            StringBuilder str = new StringBuilder();
            str.append(baseUrl).append(path);
            log.info("Constructed URL: " + str.toString());

            URL url = new URL(str.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            con.addRequestProperty("Authorization", "Bearer " + token);
            con.addRequestProperty("Content-Type", "application/json;  charset=utf-8");
            con.addRequestProperty("Accept", "application/json");

            log.info("This is the response code" + con.getResponseCode());

            // Get the response
            InputStream inputStream;
            try {
                inputStream = con.getInputStream();
            } catch (IOException e) {
                inputStream = con.getErrorStream();
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                String responseBody = response.toString();
                log.info("Response body: " + responseBody);
                exchange.setBody(responseBody);
            }
            return exchange;
        } catch (Exception e) {
            log.error("Error in processing the exchange", e);
            throw e;
        }
    }
}
