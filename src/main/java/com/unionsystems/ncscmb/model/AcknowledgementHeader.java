package com.unionsystems.ncscmb.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AcknowledgementHeader {
    @JacksonXmlProperty(localName = "Module")
    private String module = "customsduty";

    @JacksonXmlProperty(localName = "Product")
    private String product = "onlineduty";

    @JacksonXmlProperty(localName = "Status")
    private String status = "SUCCEEDED";

    @JacksonXmlProperty(localName = "EventType")
    private String eventType = "A";

    @JacksonXmlProperty(localName = "TargetSystem")
    private String targetSystem = "OPTIMUS";

    @JacksonXmlProperty(localName = "SourceSystem")
    private String sourceSystem = "TRADEX";
}
