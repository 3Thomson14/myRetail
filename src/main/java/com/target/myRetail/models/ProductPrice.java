package com.target.myRetail.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Document(collection = "productPrice")
public class ProductPrice {
    @Id
    public ObjectId _id;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public CurrentPrice getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(CurrentPrice currentprice) {
        this.currentprice = currentprice;
    }

    @Column(columnDefinition="Integer")
    public int productid;

    public CurrentPrice currentprice;

    // Constructors
    public ProductPrice() {}

    public ProductPrice(ObjectId _id, int id, CurrentPrice price) {
        this._id = _id;
        this.productid = id;
        this.currentprice = price;
    }

    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }


}
