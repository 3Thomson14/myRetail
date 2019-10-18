package com.target.myRetail.repository;

import com.target.myRetail.models.ProductName;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNameRepository extends MongoRepository<ProductName, Integer> {

    ProductName findByProductid(int productid);

}
