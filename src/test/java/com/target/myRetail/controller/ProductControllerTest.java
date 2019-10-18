package com.target.myRetail.controller;

import com.target.myRetail.models.CurrentPrice;
import com.target.myRetail.models.ProductName;
import com.target.myRetail.models.ProductPrice;
import com.target.myRetail.repository.ProductNameRepository;
import com.target.myRetail.repository.ProductPriceRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductPriceRepository productPriceRepository;

    @MockBean
    private ProductNameRepository productNameRepository;

    @Before
    public  void setup() {
       mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    private static final String productDeatils = "{\"id\":15643793,\"current_price\":{\"value\":20.49,\"currencycode\":\"USD\"}}";

    @Test
    public void testProductDetailsById() throws Exception {
        ProductPrice productPrice = new ProductPrice(new ObjectId("5da7783fa0e90a07ab40c01b"),123,new CurrentPrice(10.99, "USD"));
        ProductName productName = new ProductName(new ObjectId("5da7783fa0e90a07ab40c01b"), 123, "Samsung TV");

        when(this.productPriceRepository.findByProductid(123)).thenReturn(productPrice);
        when(this.productNameRepository.findByProductid(123)).thenReturn(productName);

        MvcResult result = this.mockMvc
            .perform(get("/products/123"))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testProductDetailsByIdNotFound() throws Exception {
        when(this.productPriceRepository.findByProductid(786)).thenReturn(null);
        when(this.productNameRepository.findByProductid(786)).thenReturn(null);

        MvcResult result = this.mockMvc
            .perform(get("/products/786"))
            .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    public void testProductDetailsByIdDataAccessException() throws Exception {
        String exceptionMessage = "DataAccessException While Fetching product details";

        when(this.productPriceRepository.findByProductid(456)).thenThrow(new DataAccessException(exceptionMessage) {
        });

        MvcResult result = this.mockMvc
            .perform(get("/products/456"))
            .andExpect(status().isInternalServerError()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertEquals(resultContent, exceptionMessage);
    }

    @Test
    public void testUpdateProductPrice() throws Exception {

        ProductPrice productPrice = new ProductPrice(new ObjectId("5da7783fa0e90a07ab40c01b"),123,new CurrentPrice(10.99, "USD"));

        when(this.productPriceRepository.findByProductid(123)).thenReturn(productPrice);

        MvcResult result = this.mockMvc.perform(put("/products/123")
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(productDeatils))
                                       .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testUpdateProductPriceForInValidProductId() throws Exception {

        when(this.productPriceRepository.findByProductid(123)).thenReturn(null);

        MvcResult result = this.mockMvc.perform(put("/products/123")
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(productDeatils))
                                       .andExpect(status().is4xxClientError()).andReturn();
    }

    @Test
    public void testUpdateProductPriceForDataAccessException() throws Exception {

        String exceptionMessage = "DataAccessException While Updating product details";

        when(this.productPriceRepository.findByProductid(123)).thenThrow(new DataAccessException(exceptionMessage) {
        });
        MvcResult result = this.mockMvc.perform(put("/products/123")
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(productDeatils))
                                       .andExpect(status().isInternalServerError()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        assertEquals(resultContent, exceptionMessage);
    }

    @Test
    public void testProductNameById() throws Exception {
        ProductName productName = new ProductName(new ObjectId("5da7783fa0e90a07ab40c01b"), 123, "Samsung TV");

        when(this.productNameRepository.findByProductid(123)).thenReturn(productName);

        MvcResult result = this.mockMvc
            .perform(get("/products/name/123"))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testProductNameByIdNotFound() throws Exception {
        when(this.productNameRepository.findByProductid(786)).thenReturn(null);

        MvcResult result = this.mockMvc
            .perform(get("/products/name/786"))
            .andExpect(status().is4xxClientError()).andReturn();
    }
}
