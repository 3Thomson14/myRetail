package com.target.myRetail.repository;

import com.target.myRetail.models.ProductName;
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
public class ProductNameRepositoryTest {

    @Autowired
    private ProductNameRepository productNameRepository;

    @Before
    public void setup() {
        ProductName productName1 = new ProductName(new ObjectId("5da7783fa0e90a07ab40c01b"),123, "Samsung TV");
        ProductName productName2 = new ProductName(new ObjectId("5da7783fa0e90a07ab40c01c"),987, "LG Refrigirator");

        productNameRepository.save(productName1);
        productNameRepository.save(productName2);
    }

    @Test
    public void testFindByProductId() {
        ProductName productName = productNameRepository.findByProductid(123);

        assertEquals(123, productName.getProductid());
        assertEquals("Samsung TV", productName.getProductname());

        productName = productNameRepository.findByProductid(987);

        assertEquals(987, productName.getProductid());
        assertEquals("LG Refrigirator", productName.getProductname());
    }

    @Test
    public void testFindByProductIdNotFound() {
        ProductName productName = productNameRepository.findByProductid(456);

        assertNull(productName);
    }

    @After
    public void cleanup() {
        productNameRepository.deleteAll();
    }
}
