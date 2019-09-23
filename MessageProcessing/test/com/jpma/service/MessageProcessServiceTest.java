/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.service;

import com.jpma.exception.APIException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Flavio
 */
public class MessageProcessServiceTest {
    
    public MessageProcessServiceTest() {
    }

    @Test
    public void testProcessMessageType() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("apple at 10p");

        assertTrue(messageProcessed);
    }
    
    @Test (expected = APIException.class)
    public void testProcessMessageType1() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("apple at 10r");

        assertFalse(messageProcessed);
    }
    
    @Test
    public void testProcessMessageType2() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("20 sales of apples at 10p each");

        assertTrue(messageProcessed);
    }
    
    @Test
    public void testProcessMessageType3() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("Add 20p apples");

        assertTrue(messageProcessed);
    }
    
    @Test
    public void testProcessMessageType1Error() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("apple at");

        assertFalse(messageProcessed);
    }
    
    @Test
    public void testProcessMessageType2Error() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("20 sales of at 10p each");

        assertFalse(messageProcessed);
    }
    
    @Test
    public void testProcessMessageType3Error1() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("20p apples");

        assertFalse(messageProcessed);
    }
    
    @Test
    public void testProcessMessageType3Error2() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("Divide 20p apples");

        assertFalse(messageProcessed);
    }
    
    @Test
    public void testProcessMessageType3Error3() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage("");

        assertFalse(messageProcessed);
    }
    
    @Test
    public void testProcessMessageType3Error4() throws APIException {

        MessageProcessService instance = new MessageProcessService();
        Boolean messageProcessed = instance.processMessage(null);

        assertFalse(messageProcessed);
    }
    
}
