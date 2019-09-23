/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.utils;

import com.jpma.exception.APIException;
import java.math.BigDecimal;

/**
 *
 * @author Flavio
 */
public class UtilsParsers {

    public static BigDecimal parsePrice(String price) throws APIException {
        try {
            if (price != null && !price.isEmpty()) {
                return new BigDecimal(price.replaceAll("£|p", ""));
            }
        } catch (Exception ex) {
            throw new APIException("parserPrice: Invalid value.", ex);
        }

        return new BigDecimal(0);
    }

    public static String parseProductType(String productType) {
        String parsedType;
        String typeWithoutLastChar = productType.substring(0, productType.length() - 1);

        if (productType.endsWith("o")) {
            parsedType = String.format("%soes", typeWithoutLastChar);
        } else if (productType.endsWith("y")) {
            parsedType = String.format("%sies", typeWithoutLastChar);
        } else if (productType.endsWith("h")) {
            parsedType = String.format("%shes", typeWithoutLastChar);
        } else if (!productType.endsWith("s")) {
            parsedType = String.format("%ss", productType);
        } else {
            parsedType = String.format("%s", productType);
        }
        return parsedType.toLowerCase();
    }

}
