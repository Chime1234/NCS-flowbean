package com.unionsystems.ncscmb;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import com.unionsystems.ncscmb.model.AssessmentFetchPeriod;
import com.unionsystems.ncscmb.util.Util;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrepareAssessmentFetchPreFlight implements FlowBean {
    private static final Logger log = LoggerFactory.getLogger(PrepareAssessmentFetchPreFlight.class);

    private static final String TIMEZONE = "Africa/Lagos";

    public FlowExchange process(FlowExchange exchange) {
        try {
            Date d = new Date();
            Util.addDays(d, -10);
            String fromDate = Util.formatDate(d);
            Long settingsId = null;
            Map<String, Object> headers = exchange.getHeader();
            long lastFireTime = 0L;
            if (exchange.getBody() instanceof Map) {
                Map<String, Object> swSettings = (Map<String, Object>)exchange.getBody();
                for (Map.Entry<String, Object> entry : swSettings.entrySet()) {
                    String key = entry.getKey();
                    if (key.equalsIgnoreCase("LAST_FIRED_TIME")) {
                        lastFireTime = (entry.getValue() instanceof BigDecimal) ? ((BigDecimal)entry.getValue()).longValue() : ((Long)entry.getValue()).longValue();
                        LocalDateTime lastFireDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastFireTime), ZoneId.of("Africa/Lagos"));
                        fromDate = lastFireDateTime.toString();
                    }
                    if (key.equalsIgnoreCase("ID"))
                        settingsId = Long.valueOf((entry.getValue() instanceof BigDecimal) ? ((BigDecimal)entry
                                .getValue()).longValue() : ((Long)entry
                                .getValue()).longValue());
                }
                headers.put("QUERY_ACTION", "update");
                headers.put("QUERY_PARAM_ID", settingsId);
            } else {
                headers.put("QUERY_ACTION", "insert");
                headers.put("QUERY_PARAM_ID", Integer.valueOf(1));
            }
            long timeDiff = Util.getDateTimeNowDiff(LocalDateTime.parse(fromDate));
            long AssessmentFetchTimeInterval = Long.parseLong((String)headers.get("ASSESSMENT_FETCH_TIME_INTERVAL"));
            List<AssessmentFetchPeriod> assessmentPeriods = new ArrayList<>();
            if (timeDiff > AssessmentFetchTimeInterval) {
                LocalDateTime now = LocalDateTime.now();
                ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Africa/Lagos"));
                long nowInMillis = zonedDateTime.toInstant().toEpochMilli();
                long toTime = lastFireTime + AssessmentFetchTimeInterval;
                while (nowInMillis > toTime) {
                    AssessmentFetchPeriod fetchPeriod = new AssessmentFetchPeriod();
                    fetchPeriod.setFromDate(Util.fromtoDate(fromDate));
                    String toDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(toTime), ZoneId.of("Africa/Lagos")).toString();
                    fetchPeriod.setToDate(Util.fromtoDate(toDate));
                    assessmentPeriods.add(fetchPeriod);
                    toTime += AssessmentFetchTimeInterval;
                    fromDate = toDate;
                }
                AssessmentFetchPeriod fetchLastPeriod = new AssessmentFetchPeriod();
                fetchLastPeriod.setFromDate(Util.fromtoDate(fromDate));
                String toNowDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(nowInMillis), ZoneId.of("Africa/Lagos")).toString();
                fetchLastPeriod.setToDate(Util.fromtoDate(toNowDate));
                assessmentPeriods.add(fetchLastPeriod);
            } else {
                AssessmentFetchPeriod fetchPeriod = new AssessmentFetchPeriod();
                fetchPeriod.setFromDate(Util.fromtoDate(fromDate));
                fetchPeriod.setToDate(LocalDateTime.now(ZoneId.of("Africa/Lagos")).toString());
                assessmentPeriods.add(fetchPeriod);
            }
            headers.put("QUERY_PARAM_LAST_FIRED_TIME", Long.valueOf(System.currentTimeMillis()));
            exchange.setBody(assessmentPeriods);
            return exchange;
        } catch (Exception $ex) {
            throw $ex;
        }
    }
}
