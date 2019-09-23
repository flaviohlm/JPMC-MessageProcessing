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
public abstract class ValueAdjustment {
   
    public void adjust(String operation, BigDecimal value) throws APIException {        
    }

    public void add(BigDecimal value) {
    }

    public void subtract(BigDecimal value) {
    }

    public void multiply(BigDecimal value) {
    }
}
