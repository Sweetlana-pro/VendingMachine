/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.service;

import com.sg.vendingmachin.dao.VendingMachinPersistenceException;
import com.sg.vendingmachin.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @Sweetlana protsenko
 */
public interface VendingMachinServiceLayer {

    List<Item> getAllItems() throws 
            VendingMachinPersistenceException;

      Item getItem(String itemNumber) throws
            VendingMachinPersistenceException;

       String buyItem(String itemNumber, BigDecimal deposit) throws
            VendingMachinPersistenceException,
            InsufficientFundsException, 
            NoItemInventoryException;


}
 
