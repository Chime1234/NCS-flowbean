package com.unionsystems.ncscmb.model;

public class AssessmentFetchPeriod {
    private String fromDate;

    private String toDate;

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AssessmentFetchPeriod))
            return false;
        AssessmentFetchPeriod other = (AssessmentFetchPeriod)o;
        if (!other.canEqual(this))
            return false;
        Object this$fromDate = getFromDate(), other$fromDate = other.getFromDate();
        if ((this$fromDate == null) ? (other$fromDate != null) : !this$fromDate.equals(other$fromDate))
            return false;
        Object this$toDate = getToDate(), other$toDate = other.getToDate();
        return !((this$toDate == null) ? (other$toDate != null) : !this$toDate.equals(other$toDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AssessmentFetchPeriod;
    }



    public String toString() {
        return "AssessmentFetchPeriod(fromDate=" + getFromDate() + ", toDate=" + getToDate() + ")";
    }

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }
}