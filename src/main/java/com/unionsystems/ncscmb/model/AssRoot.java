package com.unionsystems.ncscmb.model;

import lombok.Data;

import java.util.List;
@Data
public class AssRoot {

    private List<AssessmentData> data;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalRecords;
}
