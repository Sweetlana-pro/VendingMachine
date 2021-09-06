/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.dto;

import java.math.BigDecimal;

/**
 *
 * @author pro
 */
public class Change {
    Item item;

    private int numQuarters, numDimes, numNickels, numPennies;


    public BigDecimal makeChange(Item item, BigDecimal deposit) {

        BigDecimal penny = new BigDecimal("1");
        BigDecimal nickle = new BigDecimal("5");
        BigDecimal dime = new BigDecimal("10");
        BigDecimal quarter = new BigDecimal("25");
        BigDecimal cents = new BigDecimal("100");

        BigDecimal price = item.getItemPrice();


        BigDecimal totalChange = deposit.subtract(price);

        BigDecimal myChange = totalChange.multiply(cents);

        numQuarters = 0;
        numDimes = 0;
        numNickels = 0;
        numPennies = 0;

        while (myChange.floatValue() > 0) {
            
            if (myChange.floatValue() >= 25) {
                myChange =  myChange.subtract(quarter);
                numQuarters++;
            } else if (myChange.floatValue() >= 10) {
                myChange =  myChange.subtract(dime);
                numDimes++;
            } else if (myChange.floatValue() >= 5) {
                myChange =  myChange.subtract(nickle);
                numNickels++;
            } else if (myChange.floatValue() >= 1) {
                myChange =  myChange.subtract(penny);
                numPennies++;
                }
            
        }
        return totalChange;

    }
    

    public int getQuarters() {
        return numQuarters;
    }

    public int getDimes() {
        return numDimes;
    }

    public int getNickels() {
        return numNickels;
    }

    public int getPennies() {
        return numPennies;
    }

}
