/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.service;

import java.math.BigDecimal;
import com.jpma.domain.Sale;
import com.jpma.exception.APIException;
import com.jpma.utils.UtilsParsers;

/**
 *
 * @author Flavio
 */
public class MessageProcessService extends SalesStorageService{
    
    /*
    * For each message, the application verify the type of the message.
    * If it is Type 3, it means it is an adjustment operation for a certain product type starting
    * The string line which one of the three definied operations Add, Subtract or Muiltiply.
    * If it is Type 1 or 2 it means it is a simple or multiple sale.    
    */
    public Boolean processMessage(String saleMessage) throws APIException {
        
        if(saleMessage==null){
            System.out.println("Notice: Incorrect message format -> " + saleMessage);
            return false;
        }
        
        String[] message = saleMessage.trim().split("\\s+");

        if (message[0].matches("Add|Subtract|Multiply")) {//Message Type 3
            String operation = message[0];
            BigDecimal adjustmentValue = UtilsParsers.parsePrice(message[1]);
            String productType = UtilsParsers.parseProductType(message[2]);

            //Call the adjustiment method, which will change the value of all sales stored.
            adjustPrice(operation, adjustmentValue, productType);

        } else {
            if (message.length == 7 && message[0].matches("^\\d+")) {//Message Type 2                        
                Sale sale = new Sale();
                sale.setQuantity(Integer.parseInt(message[0]));
                sale.setProductType(UtilsParsers.parseProductType(message[3]));
                sale.setProductValue(UtilsParsers.parsePrice(message[5]));
                
                addSalesStorage(sale);//Add the sale with the quantity informed
            } else if (message.length == 3 && message[1].contains("at")) {//Message Type 1
                Sale sale = new Sale();
                sale.setQuantity(1);
                sale.setProductType(UtilsParsers.parseProductType(message[0]));
                sale.setProductValue(UtilsParsers.parsePrice(message[2]));
                
                addSalesStorage(sale);//Add the sale with the quantity equals 1, because it is omitted in the message
            } else {
                System.out.println("Notice: Incorrect message format -> " + saleMessage);
                return false;
            }            
        }
        return true;
    }
}
