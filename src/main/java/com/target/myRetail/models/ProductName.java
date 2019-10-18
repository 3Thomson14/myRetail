package com.target.myRetail.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Document(collection = "productName")
public class ProductName {

    @Id
    public ObjectId _id;

    @Column(columnDefinition="Integer")
    private int productid;

    private String productname;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public ProductName(ObjectId _id, int productid, String productname) {
        this._id = _id;
        this.productid = productid;
        this.productname = productname;
    }
}
