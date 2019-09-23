/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import com.jpma.domain.Sale;
import com.jpma.exception.APIException;

/**
 *
 * @author Flavio
 */
public class SalesStorageService {
    
    public static HashMap<String, ArrayList> salesStorage = new HashMap<>(); //Where the sales will be stored    
    public ArrayList<String> operationsLog = new ArrayList<>(); //The storage for the adjustments operation

    public void addSalesStorage(Sale sale) {
        ArrayList<Sale> newList = salesStorage.get(sale.getProductType());
        if (newList == null) {
            newList = new ArrayList<>();
            newList.add(sale);
            salesStorage.put(sale.getProductType(), newList);
        } else {
            newList.add(sale);
        }
    }

    public Boolean adjustPrice(String operation, BigDecimal value, String key) throws APIException {
        if (!salesStorage.isEmpty() && salesStorage.get(key) != null) {
            ArrayList<Sale> sales = salesStorage.get(key);

            Integer quantity = 0;
            BigDecimal total = BigDecimal.ZERO;
            BigDecimal totalAntigo = BigDecimal.ZERO;

            for (Sale s : sales) {
                totalAntigo = totalAntigo.add(s.getProductValueTotal());

                s.adjust(operation, value);

                quantity += s.getQuantity();
                total = total.add(s.getProductValueTotal());
            }

            String adjustmentReport = String.format("Price Adjustment: %s %.2fp to %d %s. Price adjusted from %.2fp to %.2fp", operation, value, quantity, key, totalAntigo, total);

            operationsLog.add(adjustmentReport);
            return true;
        }
        return false;
    }

    public void showAdjustPriceOperationsLog() {
        System.out.println(String.format("%-40s", "------------------------------- ADJUST PRICE OPERATIONS LOG ----------------------------------"));
        for (String s : operationsLog) {
            System.out.println(s);
        }
        System.out.println(String.format("%-40s", "----------------------------------------------------------------------------------------------"));
    }

    public void showSalesLog() {
        System.out.println(String.format("%-40s", "\n--------------- SALES LOG -----------------"));
        System.out.println(String.format("%-20s|%-10s|%-10s|", "PRODUCT TYPE", "QUANTITY", "TOTAL"));

        for (String key : salesStorage.keySet()) {
            ArrayList<Sale> value = salesStorage.get(key);
            Integer quantity = 0;
            BigDecimal total = BigDecimal.ZERO;

            for (Sale s : value) {
                quantity += s.getQuantity();
                total = total.add(s.getProductValueTotal());
            }
            formatLines(key, quantity, total);
        }
        System.out.println(String.format("%-40s", "-------------------------------------------\n"));
    }

    public void formatLines(String key, Integer quantity, BigDecimal total) {
        String lineItem = String.format("%-20s|%-10d|%-10.2f|", key, quantity, total);
        System.out.println(lineItem);
    }

}
