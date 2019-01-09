package com.griddynamics.Store;

import com.griddynamics.Store.model.Item;
import com.griddynamics.Store.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StoreApplication.class)
@WebAppConfiguration
public class ItemControllerTest extends  AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void whenAddItemReturnConfirmationString() throws Exception {
        Item newItem = new Item(1l, 1);
        String inputJson = super.mapToJson(newItem);
        mvc.perform(post("/addItem")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().equals("Product added to cart"));
    }

    @Test
    public void whenTryToDeleteNonExistingItemInCart() throws Exception {
        MvcResult mvcResult = mvc.perform(delete("/removeItem/1"))
                .andReturn();

        String code = mvcResult.getResponse().getContentAsString();
        assertEquals("204", code);
    }
}
