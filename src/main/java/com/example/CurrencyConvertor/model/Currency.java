package com.example.CurrencyConvertor.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Currency implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer currencyID;
    private String currencyNameLT;
    private String currencyNameEN;
    private String shortCurrencyName;
    private Double exchangeRate;
    @Column(name = "rateChangingDate")
    private Date date;


    public Currency() {
    }


    public Currency(String shortCurrencyName,String currencyNameLT, String currencyNameEN,  Double exchangeRate, Date date) {
        this.shortCurrencyName = shortCurrencyName;
        this.currencyNameLT = currencyNameLT;
        this.currencyNameEN = currencyNameEN;
        this.exchangeRate = exchangeRate;
        this.date = date;
    }

    public Currency(String ccy, String ccyNm, String ccyNm1) {
        this.shortCurrencyName = ccy;
        this.currencyNameLT = ccyNm;
        this.currencyNameEN = ccyNm1;
    }

    public String getCurrencyNameEN() {
        return currencyNameEN;
    }

    public void setCurrencyNameEN(String currencyNameEN) {
        this.currencyNameEN = currencyNameEN;
    }

    public String getShortCurrencyName() {
        return shortCurrencyName;
    }

    public void setShortCurrencyName(String shortCurrencyName) {
        this.shortCurrencyName = shortCurrencyName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrencyNameLT() {
        return currencyNameLT;
    }

    public void setCurrencyNameLT(String currencyNameLT) {
        this.currencyNameLT = currencyNameLT;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


    @Override
    public String toString() {
        return "Currency{" +
                "currencyID=" + currencyID +
                ", currencyNameLT='" + currencyNameLT + '\'' +
                ", currencyNameEN='" + currencyNameEN + '\'' +
                ", shortCurrencyName='" + shortCurrencyName + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", date=" + date +
                '}';

    }
}
