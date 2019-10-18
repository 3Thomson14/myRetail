package com.target.myRetail.models;

public class CurrentPrice {
    public double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public String currencycode;

    public CurrentPrice(double value, String currencycode) {
        this.value = value;
        this.currencycode = currencycode;
    }
}
