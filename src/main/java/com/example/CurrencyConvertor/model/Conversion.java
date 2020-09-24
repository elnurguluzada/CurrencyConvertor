package com.example.CurrencyConvertor.model;

public class Conversion {

    private String fromCurrency;
    private String toCurrency;
    private double value;

    public Conversion() {
    }

    public Conversion(String fromCurrency, String toCurrency, double value) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.value = value;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Conversion{" +
                "fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", value=" + value +
                '}';
    }
}
