/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.service;

import com.jpma.domain.Sale;
import com.jpma.exception.APIException;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Flavio
 */
public class SalesStorageServiceTest {
    
    public SalesStorageServiceTest() {
    }

    @Test
    public void testAddSalesStorage() {
        SalesStorageService instance = new SalesStorageService();
        
        Sale sale = new Sale();
        sale.setProductType("Apple");
        sale.setProductValue(BigDecimal.valueOf(0.10));
        sale.setQuantity(1);
        instance.addSalesStorage(sale);
              
        assertFalse(SalesStorageService.salesStorage.isEmpty());
    }

    @Test
    public void testAdjustPrice1() throws APIException {
        SalesStorageService instance = new SalesStorageService();
        
        Boolean messageProcessed = instance.adjustPrice("ADD", BigDecimal.ONE, "Apple");        
        assertFalse(messageProcessed);
    }
    
    @Test
    public void testAdjustPrice2() throws APIException {
        SalesStorageService instance = new SalesStorageService();
        
        Sale sale = new Sale();
        sale.setProductType("Apple");
        sale.setProductValue(BigDecimal.valueOf(0.10));
        sale.setQuantity(1);
        instance.addSalesStorage(sale);        
        
        Boolean messageProcessed = instance.adjustPrice("Subtract", BigDecimal.ONE, "Apple");        
        assertTrue(messageProcessed);
    }
    
    @Test
    public void testAdjustPrice3() throws APIException {
        SalesStorageService instance = new SalesStorageService();
        
        Boolean messageProcessed = instance.adjustPrice("Multiply", BigDecimal.ONE, "Banana");
        assertFalse(messageProcessed);
    }
    
    @Test (expected = APIException.class)
    public void testAdjustPrice4() throws APIException {
        SalesStorageService instance = new SalesStorageService();
        
        Boolean messageProcessed = instance.adjustPrice("Divide", BigDecimal.ONE, "Apple");
        assertFalse(messageProcessed);
    }
    
}
