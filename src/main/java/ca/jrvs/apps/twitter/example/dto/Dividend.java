package ca.jrvs.apps.twitter.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dividend {


    private Date exDate;
    private Date paymentDate;
    private Date recordDate;
    private Date declaredDate;
    private double amount;

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getDeclaredDate() {
        return declaredDate;
    }

    public void setDeclaredDate(Date declaredDate) {
        this.declaredDate = declaredDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
