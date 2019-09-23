/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.controller;

import com.jpma.exception.APIException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;
import com.jpma.service.MessageProcessService;

/**
 *
 * @author Flavio
 */
public class ProcessFileMessageController {

    private final Integer LIMIT_MESSAGES = 50;//The limite of messages to be processed by this application
    private final Integer INTERVAL_SHOW_MESSAGES = 10;//The message reading interval to show the log sales

    private MessageProcessService salesStorageService = new MessageProcessService();

    /*
    * The choosen interface for the message processing was by file reading.
    */
    public void readFile(String filePath) throws APIException {
        BufferedReader bufferedReader;
        String saleMessage;
        Integer count = 0;
        try {
            File inFile = new File(filePath);
            bufferedReader = new BufferedReader(new FileReader(inFile));
            while ((saleMessage = bufferedReader.readLine()) != null) {
                count++;

                salesStorageService.processMessage(saleMessage);//All messages in the file are processed
                
                if (count % INTERVAL_SHOW_MESSAGES == 0) {//In each multiple of 10, show the the LOG of sales
                    salesStorageService.showSalesLog();
                }
                if (Objects.equals(count, LIMIT_MESSAGES)) {//When reach the limit of messages, show the Log of adjustments and stop the application.
                    System.out.println("\n\nNotice: Messages processing is pausing. " + LIMIT_MESSAGES + " messages have been reached.\n");
                    salesStorageService.showAdjustPriceOperationsLog();
                    break;
                }
            }
        } catch (java.io.IOException|NullPointerException ex) {
            throw new APIException("ProcessFileMessageController.readFile: Invalid path.", ex);
        }
    }

    
    
    public MessageProcessService getSalesStorageService() {
        return salesStorageService;
    }

    public void setSalesStorageService(MessageProcessService salesStorageService) {
        this.salesStorageService = salesStorageService;
    }
}
