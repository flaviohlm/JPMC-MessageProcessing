/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.domain;

import com.jpma.exception.APIException;
import java.math.BigDecimal;

/**
 *
 * @author Flavio
 */
public class Sale extends ValueAdjustment {

    private String productType;
    private BigDecimal productValue;
    private Integer quantity;
    private BigDecimal productValueTotal;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductValueTotal() {
        productValueTotal = BigDecimal.ZERO;

        if (!this.getProductValue().equals(0) && this.quantity != 0) {
            productValueTotal = getProductValue().multiply(new BigDecimal(this.quantity));
        }

        return productValueTotal;
    }

    public void setProductValueTotal(BigDecimal productValueTotal) {
        this.productValueTotal = productValueTotal;
    }

    @Override
    public void adjust(String operation, BigDecimal value) throws APIException {
        if (operation.equalsIgnoreCase("add")) {
            add(value);
        } else if (operation.equalsIgnoreCase("subtract")) {
            subtract(value);
        } else if (operation.equalsIgnoreCase("multiply")) {
            multiply(value);
        } else {
            throw new APIException("Sale.adjust: Operation not suported.", new Exception());
        }
    }

    @Override
    public void add(BigDecimal value) {
        productValue = productValue.add(value);
    }

    @Override
    public void subtract(BigDecimal value) {
        productValue = productValue.subtract(value);
    }

    @Override
    public void multiply(BigDecimal value) {
        productValue = productValue.multiply(value);
    }
}
