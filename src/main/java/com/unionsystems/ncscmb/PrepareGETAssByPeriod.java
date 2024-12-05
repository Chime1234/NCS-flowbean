package com.unionsystems.ncscmb;
import com.unionsystems.ncscmb.model.AssessmentDutyList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrepareGETAssByPeriod implements FlowBean {
    private static final Logger log = LoggerFactory.getLogger(PrepareGETAssByPeriod.class);

    public FlowExchange process(FlowExchange exchange) throws Exception {
        Map<String, Object> exheaders = exchange.getHeader();
        ObjectMapper mapper = new ObjectMapper();
        String assUrl = exheaders.get("ASS_BASE_URL").toString();
        String startDate = exheaders.get("START_DATE").toString();
        String startDateEncoded = URLEncoder.encode(startDate, "UTF-8");
        String endDate = exheaders.get("END_DATE").toString();
        String endDateEncoded = URLEncoder.encode(endDate, "UTF-8");
        String token = exheaders.get("ASS_TOKEN").toString();
        StringBuilder strUrl = new StringBuilder();
        strUrl.append(assUrl).append("&startDate").append(startDateEncoded)
                .append("&endDate").append(endDateEncoded);
        String result = null;
        AssessmentDutyList assessmentDutyList = null;
        TrustManager[] trustAllCerts = { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {}

            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        } };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        log.info(">>> request url :" + strUrl);
        HttpURLConnection con = null;
        try {
            URL url = new URL(strUrl.toString());
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "*/*");
            con.setDoOutput(true);
            con.setDoInput(true);
            log.info(">>>> Response code :" + con.getResponseCode());
            InputStream inputStream = null;
            try {
                inputStream = con.getInputStream();
            } catch (IOException e) {
                inputStream = con.getErrorStream();
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null)
                    response.append(line);
                in.close();
                result = response.toString();
                assessmentDutyList = (AssessmentDutyList)mapper.readValue(result, AssessmentDutyList.class);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        exchange.setBody(assessmentDutyList.getData());
        return exchange;
    }
}

