/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.dao;

import com.sg.vendingmachin.dto.Item;
import java.util.List;

/**
 *
 * @Sweetlana Protsenko
 */
public interface VendingMachinDao {
     List<Item> getAllItems()
            throws VendingMachinPersistenceException;

    Item getItem(String itemNumber)
             throws VendingMachinPersistenceException;

    void buyItem(String itemNumber)
             throws VendingMachinPersistenceException;

}
