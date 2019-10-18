package com.target.myRetail.entities;

import com.target.myRetail.models.CurrentPrice;

public class ProductDetailsRequest {

    private int id;

    private CurrentPrice current_price;

    public ProductDetailsRequest(int id, CurrentPrice current_price) {
        this.id = id;
        this.current_price = current_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CurrentPrice getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(CurrentPrice current_price) {
        this.current_price = current_price;
    }
}
