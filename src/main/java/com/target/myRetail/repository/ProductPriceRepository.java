package com.target.myRetail.repository;

import com.target.myRetail.models.ProductPrice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends MongoRepository<ProductPrice, Integer> {

    ProductPrice findByProductid(int productid);

}
