package com.unionsystems.ncscmb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class NcsApplication {

        // The JSON string as a variable
        private String json = "{\n" +
                "  \"id\" : 7756648,\n" +
                "  \"version\" : 0,\n" +
                "  \"paymentDate\" : \"2024-10-04\",\n" +
                "  \"customsCode\" : \"01TG\",\n" +
                "  \"companyCode\" : \"2354234\",\n" +
                "  \"declarantCode\" : \"1231\",\n" +
                "  \"bankCode\" : \"33\",\n" +
                "  \"sadRegSerial\" : \"A\",\n" +
                "  \"sadRegNumber\" : \"42815\",\n" +
                "  \"sadYear\" : \"2024\",\n" +
                "  \"amount\" : 10602343,\n" +
                "  \"payments\" : [ {\n" +
                "    \"meansOfPayment\" : \"11\",\n" +
                "    \"reference\" : \"0704281\",\n" +
                "    \"amount\" : 10602343\n" +
                "  } ]\n" +
                "}";

        // Method to format JSON to a single line
        public String formatJson() {
            return json.replaceAll("\\s+", " ");
        }

        public static void main(String[] args) {
            NcsApplication formatter = new NcsApplication();
            String singleLineJson = formatter.formatJson();
            System.out.println(singleLineJson);
        }
    }
