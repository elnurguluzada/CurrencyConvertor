package com.example.CurrencyConvertor.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

@Entity
public class Currency implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer currencyID;
    private String currencyNameLT;
    private String currencyNameEN;
    private String shortCurrencyName;
    private float exchangeRate;
    private Date date;


    public Currency() {
    }

    public Currency( Date date,String shortCurrencyName, float exchangeRate ) {

        this.date= date;
        this.shortCurrencyName = shortCurrencyName;
        this.exchangeRate = exchangeRate;

    }


    public Currency(String shortCurrencyName,String currencyNameLT, String currencyNameEN ) {
        this.shortCurrencyName = shortCurrencyName;
        this.currencyNameLT = currencyNameLT;
        this.currencyNameEN = currencyNameEN;

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

    public float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(float exchangeRate) {
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
