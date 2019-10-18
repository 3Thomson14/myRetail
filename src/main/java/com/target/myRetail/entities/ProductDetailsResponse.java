package com.target.myRetail.entities;

import com.target.myRetail.models.CurrentPrice;
import com.target.myRetail.models.ProductPrice;

public class ProductDetailsResponse {

    private int id;

    private String name;

    private CurrentPrice current_price;

    public ProductDetailsResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductDetailsResponse(int id, String name, CurrentPrice current_price) {
        this.id = id;
        this.name = name;
        this.current_price = current_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrentPrice getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(CurrentPrice current_price) {
        this.current_price = current_price;
    }
}
