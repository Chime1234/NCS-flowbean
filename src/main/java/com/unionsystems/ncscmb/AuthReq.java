package com.unionsystems.ncscmb;


import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Map;

@Slf4j
public class AuthReq implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        try {
            // Extract headers from FlowExchange
            Map<String, Object> headers = exchange.getHeader();
            String baseUrl = headers.get("API_BASE_URL").toString();
            String path = headers.get("AUTH_PATH").toString();

            // Extract incoming JSON from the exchange body
            String incomingJson = exchange.getBody().toString();


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
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");


            try (OutputStream os = con.getOutputStream()) {
                byte[] input = incomingJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

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