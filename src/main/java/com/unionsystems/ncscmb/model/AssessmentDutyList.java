package com.unionsystems.ncscmb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unionsystems.ncscmb.model.AssessmentData;
import lombok.Data;

import java.util.List;
@Data
public class AssessmentDutyList {
    @JsonProperty("data")
    private List<AssessmentNotice> data;

    @JsonProperty("pageNumber")
    private int pageNumber;

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty("totalPages")
    private int totalPages;

    @JsonProperty("totalRecords")
    private int totalRecords;

    @JsonProperty("data")
    public void setData(List<AssessmentNotice> data) {
        this.data = data;
    }

    @JsonProperty("pageNumber")
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @JsonProperty("pageSize")
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("totalPages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @JsonProperty("totalRecords")
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }


}

