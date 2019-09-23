/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.controller;

import com.jpma.exception.APIException;
import org.junit.Test;

/**
 *
 * @author Flavio
 */
public class ProcessFileMessageControllerTest {
    
    public ProcessFileMessageControllerTest() {
    }

    
    @Test
    public void testReadFile() throws APIException {
        ProcessFileMessageController instance = new ProcessFileMessageController();
        instance.readFile("caseTest/messages.txt");        
    }
    
    @Test (expected = APIException.class)
    public void testReadFile2() throws APIException {
        ProcessFileMessageController instance = new ProcessFileMessageController();
        instance.readFile("caseTest/messagesx.txt");        
    } 
    
    @Test (expected = APIException.class)
    public void testReadFile3() throws APIException {
        ProcessFileMessageController instance = new ProcessFileMessageController();
        instance.readFile(null);        
    } 
}
