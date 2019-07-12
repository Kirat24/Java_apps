package ca.jrvs.apps.twitter.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)

public class Financial {


    private Date reportDate;
    private BigDecimal grossProfit;
    private BigDecimal costOfRevenue;
    private BigDecimal totalRevenue;
    private BigDecimal operatingRevenue;
    private BigDecimal operatingIncome;
    private BigDecimal netIncome;


    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    public BigDecimal getCostOfRevenue() {
        return costOfRevenue;
    }

    public void setCostOfRevenue(BigDecimal costOfRevenue) {
        this.costOfRevenue = costOfRevenue;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getOperatingRevenue() {
        return operatingRevenue;
    }

    public void setOperatingRevenue(BigDecimal operatingRevenue) {
        this.operatingRevenue = operatingRevenue;
    }

    public BigDecimal getOperatingIncome() {
        return operatingIncome;
    }

    public void setOperatingIncome(BigDecimal operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }
}
