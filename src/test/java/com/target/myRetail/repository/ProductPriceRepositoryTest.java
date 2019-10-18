package com.target.myRetail.repository;

import com.target.myRetail.models.CurrentPrice;
import com.target.myRetail.models.ProductPrice;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductPriceRepositoryTest {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Before
    public void setup() {
        ProductPrice productPrice1 = new ProductPrice(new ObjectId("5da7783fa0e90a07ab40c01b"),123,new CurrentPrice(10.99, "USD"));
        ProductPrice productPrice2 = new ProductPrice(new ObjectId("5da7783fa0e90a07ab40c01c"),456,new CurrentPrice(19.99, "USD"));

        productPriceRepository.save(productPrice1);
        productPriceRepository.save(productPrice2);
    }

    @Test
    public void testFindByProductId() {
        ProductPrice productPrice = productPriceRepository.findByProductid(123);

        assertEquals(123, productPrice.getProductid());
        assertEquals(10.99, productPrice.getCurrentprice().value, 0);

        productPrice = productPriceRepository.findByProductid(456);

        assertEquals(456, productPrice.getProductid());
        assertEquals(19.99, productPrice.getCurrentprice().value, 0);
    }

    @Test
    public void testFindByProductIdNotFound() {
        ProductPrice productPrice = productPriceRepository.findByProductid(789);

        assertNull(productPrice);
    }

    @After
    public void cleanup() {
        productPriceRepository.deleteAll();
    }
}
