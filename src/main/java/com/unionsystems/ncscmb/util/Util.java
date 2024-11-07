package com.unionsystems.ncscmb.util;

import java.io.*;
import java.math.BigDecimal;
//import java.sql.Date;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.net.ssl.*;
import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Map;

public class Util {
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    public static final String STATUS_ACTIVE = "A";

    public static final String STATUS_INACTIVE = "I";

    public static final Integer ONE_INTEGER = Integer.valueOf(1);

    public static final Integer ZERO_INTEGER = Integer.valueOf(0);

    public static final String LOCAL_CCY_CODE = "NGN";

    public static final String BASE_CCY_CODE = "USD";

    public static final BigDecimal BIGDECIMAL_ZERO_VALUE = new BigDecimal("0.00");

    public static final BigDecimal BIGDECIMAL_ONE_VALUE = new BigDecimal("1.00");

    public static String formatDate(Date d) {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).format(d);
    }

    public static void addDays(Date d, int days) {
        d.setTime(d.getTime() + days * 1000L * 60L * 60L * 24L);
    }

    public static String nullSafeStringGet(String val) {
        return (val == null) ? "" : val.trim();
    }

    public static boolean isNullOrBlank(String value) {
        if (value == null)
            return true;
        if (value.trim().length() == 0)
            return true;
        return false;
    }

    public static boolean isNullObject(Object object) {
        return (null == object);
    }

    public static BigDecimal getBigDecimal(String val) {
        return getBigDecimal(val, null);
    }

    public static BigDecimal getBigDecimal(String val, BigDecimal def) {
        val = val.replaceAll(",", "");
        try {
            return new BigDecimal(val);
        } catch (Exception e) {
            return def;
        }
    }

    public static Date getDateValue(String d, DateFormat df) throws ParseException {
        if (d == null)
            return null;
        d = d.trim();
        if (d.length() == 0)
            return null;
        return df.parse(d);
    }

    public static Date getDateValue(String d, String pattern) {
        if (d == null)
            return null;
        d = d.trim();
        if (d.length() == 0)
            return null;
        DateFormat df = new SimpleDateFormat(pattern);
        Date ret = null;
        try {
            ret = df.parse(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static Date convertSQLDateToJavaUtilDate(Date sqlDate) {
        return (null == sqlDate) ? null : new Date(sqlDate.getTime());
    }

    public static Date convertJavaUtilDateToSQLDate(Date date) {
        return (null == date) ? null : new Date(date.getTime());
    }

    public static Date getTodaysDate() {
        Calendar calRoll = Calendar.getInstance();
        calRoll.set(10, calRoll.getActualMinimum(10));
        calRoll.set(9, 0);
        calRoll.set(12, calRoll.getActualMinimum(12));
        calRoll.set(13, calRoll.getActualMinimum(13));
        calRoll.set(14, calRoll.getActualMinimum(14));
        return calRoll.getTime();
    }

    public static Date getCurrentDate() {
        return convertJavaUtilDateToSQLDate(getTodaysDate());
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
        XMLGregorianCalendar calendar;
        if (date == null)
            return null;
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(date);
            calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return calendar;
    }

    public static XMLGregorianCalendar convertSqlDateToXmlGregorianCalendar(Date sqlDate) {
        if (sqlDate == null)
            return null;
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(sqlDate);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date convertXmlGregorianCalendarToSqlDate(XMLGregorianCalendar calDate) {
        return (calDate == null) ? null : new Date(calDate.toGregorianCalendar().getTimeInMillis());
    }

    public static byte[] hexStringToByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

    public static Object findByKeyIgnoreCase(String key, Map<String, Object> map) {
        if (map == null)
            return null;
        for (String k : map.keySet()) {
            if (k.equalsIgnoreCase(key))
                return map.get(k);
        }
        return null;
    }

    public static String toXml(Node domNode) throws TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("method", "xml");
        transformer.setOutputProperty("standalone", "yes");
        transformer.setOutputProperty("omit-xml-declaration", "yes");
        transformer.setOutputProperty("encoding", "UTF-8");
        DOMSource source = new DOMSource(domNode);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(baos);
        transformer.transform(source, result);
        return baos.toString();
    }

    public static String toXmlNoWhiteSpace(Node domNode) throws TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty("indent", "no");
        transformer.setOutputProperty("standalone", "yes");
        transformer.setOutputProperty("omit-xml-declaration", "yes");
        DOMSource source = new DOMSource(domNode);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(baos);
        transformer.transform(source, result);
        return baos.toString();
    }

    public static String stackTraceToString(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    @SneakyThrows
    public static String getTokenCall(String authUrl, String path, String incomingJson) {
        try {
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
            str.append(authUrl).append(path);
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
                return responseBody;
            }
        } catch (Exception e) {
            log.error("Error in processing the exchange", e);
            throw e;
        }
    }

    public static Long LongValue(Object o) {
        return (o == null) ? null : Long.valueOf((o instanceof BigDecimal) ? ((BigDecimal)o).longValue() : ((Long)o).longValue());
    }

    public static boolean BoolValue(Object o) {
        return (o == null) ? false : ((o instanceof BigDecimal) ? Boolean.valueOf(((BigDecimal)o).toString()) : (Boolean)o).booleanValue();
    }

    public static String StringValue(Object o) {
        return (o == null) ? null : ((o instanceof BigDecimal) ? ((BigDecimal)o).toString() : (String)o);
    }

    public static Date DateValue(Object o) {
        return (o == null) ? null : ((o instanceof Timestamp) ? new Date(((Timestamp)o).getTime()) : (Date)o);
    }

    public static void main(String[] args) {
        System.out.println(LongValue(null));
    }


}
