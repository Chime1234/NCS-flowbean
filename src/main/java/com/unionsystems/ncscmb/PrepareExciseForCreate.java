package com.unionsystems.ncscmb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionsystems.ncscmb.model.AssessmentData;
import com.unionsystems.ncscmb.model.ExciseAssessmentNotice;
import com.unionsystems.ncscmb.model.Taxes;
import com.unionsystems.ncscmb.util.Util;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Slf4j
public class PrepareExciseForCreate implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        try {
            Map<String, Object> xchgHeader = exchange.getHeader();
            String json = exchange.getBody().toString();

            // Parse JSON payload to AssessmentNotice object
            ObjectMapper objectMapper = new ObjectMapper();
            ExciseAssessmentNotice notice = objectMapper.readValue(json, ExciseAssessmentNotice.class);


            Long maxId = Util.LongValue(xchgHeader.get("MAX_ASS_ID"));
            Map<String, Object> code = (Map<String, Object>) xchgHeader.get("CUSTOM_PORT");

            // Update headers with values from AssessmentNotice object
            xchgHeader.put("MAX_ASS_ID", Long.valueOf((maxId == null) ? 1L : maxId.longValue()));
            xchgHeader.put("ID", xchgHeader.get("MAX_ASS_ID"));
            xchgHeader.put("CREATED_DATE", Util.getCurrentDate());
            xchgHeader.put("SYS_CREATE_TS", new Timestamp(System.currentTimeMillis()));
            xchgHeader.put("VERSION_NO", notice.getVersion());
            xchgHeader.put("AMOUNT_PAYABLE", notice.getTotalAmountToBePaid());
            xchgHeader.put("COMPANY_CD", notice.getCompanycode());
            xchgHeader.put("CUSTOM_CD", notice.getCustomscode());
            xchgHeader.put("PORT_NAME", (code == null) ? null : Util.findByKeyIgnoreCase("VALUE", code));
            xchgHeader.put("SAD_REG_DATE", new Date(
                    Util.getDateValue(notice.getAssessmentDate(), new SimpleDateFormat("yyyy-MM-dd")).getTime()));
            xchgHeader.put("SAD_REG_NO", notice.getAssessmentNumber());
            xchgHeader.put("SAD_REG_SERIAL", notice.getAssessmentSerial());
            xchgHeader.put("SAD_YEAR", notice.getYear());
            xchgHeader.put("BANK_CODE", notice.getBankCode());

            // Convert taxes section to Java objects and set as exchange body
            Taxes taxes = notice.getTaxes();
            exchange.setBody(taxes.getTax());

            return exchange;
        } catch (Exception e) {
            log.error("Error processing exchange", e);
            throw e;
        }
    }
}