package com.target.myRetail.controller;

import com.target.myRetail.entities.ProductDetailsRequest;
import com.target.myRetail.entities.ProductDetailsResponse;
import com.target.myRetail.models.ProductName;
import com.target.myRetail.models.ProductPrice;
import com.target.myRetail.repository.ProductNameRepository;
import com.target.myRetail.repository.ProductPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private ProductNameRepository productNameRepository;

    /**
     * Returns Product Details i.e. name, current price for a given product id
     *
     * @param id product Id
     * @return ProductDetailsResponse
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductDetailsById(@PathVariable("id") int id) {
        ResponseEntity response;
        ProductDetailsResponse productDetailsResponse;
        try {

            ProductPrice productPrice = productPriceRepository.findByProductid(id);
            /**
             * Product Name reading logic can be moved as separate external exposed Rest service and Invoke that service
             * from this app to get product name
             */
            ProductName productName = productNameRepository.findByProductid(id);

            if (productName != null && productPrice != null) {
                productDetailsResponse = new ProductDetailsResponse(id, productName.getProductname(), productPrice.getCurrentprice());
                response = new ResponseEntity(productDetailsResponse, HttpStatus.OK);
            } else {
                response = new ResponseEntity("Product Id not found", HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException dae) {
            logger.error("Error fetching Product Id details from database: ", dae);
            response = new ResponseEntity(dae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Error fetching product id details: ", e);
            response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Returns Product Name information
     *
     * @param id product id
     * @return Returns Product Name info
     */
    @RequestMapping(value = "/name/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductNameById(@PathVariable("id") int id) {
        ResponseEntity response;
        ProductDetailsResponse productDetailsResponse;
        try {
            ProductName productName = productNameRepository.findByProductid(id);
            if (productName != null) {
                response = new ResponseEntity(productName, HttpStatus.OK);
            } else {
                response = new ResponseEntity("Product Id not found", HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException dae) {
            logger.error("Error fetching Product Name details from database: ", dae);
            response = new ResponseEntity(dae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Error fetching product Name details: ", e);
            response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Update the current price for a given product id
     *
     * @param id product id
     * @param productDetailsRequest
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProductPrice(@NotNull
                                                @PathVariable("id") int id, @Valid
    @RequestBody
        ProductDetailsRequest productDetailsRequest) {
        ResponseEntity response;
        try {
            ProductPrice productPrice = productPriceRepository.findByProductid(id);
            if (productPrice != null) {
                productPrice.setCurrentprice(productDetailsRequest.getCurrent_price());
                productPriceRepository.save(productPrice);
                response = new ResponseEntity("Price Updated Successfully", HttpStatus.OK);
            } else {
                response = new ResponseEntity("Product Id not found", HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException dae) {
            logger.error("Couldn't update the product price due to Database issue: ", dae);
            response = new ResponseEntity(dae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Couldn't update the product details: ", e);
            response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }
}