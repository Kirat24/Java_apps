package ca.jrvs.apps.twitter.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)


public class Comapny {

    private String symbol;
    private String comapnyName;
    private String exchange;
    private String description;
    private String CEO;
    private String sector;
    private List<Financial> financials;
    private List<Dividend> dividends;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Financial> getFinancials() {
        return financials;
    }

    public void setFinancials(List<Financial> financials) {
        this.financials = financials;
    }

    public List<Dividend> getDividends() {
        return dividends;
    }

    public void setDividends(List<Dividend> dividends) {
        this.dividends = dividends;
    }

    public String getComapnyName() {
        return comapnyName;
    }

    public void setComapnyName(String comapnyName) {
        this.comapnyName = comapnyName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCEO() {
        return CEO;
    }

    public void setCEO(String CEO) {
        this.CEO = CEO;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
