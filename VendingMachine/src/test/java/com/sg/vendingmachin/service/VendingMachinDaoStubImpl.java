/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.service;

import com.sg.vendingmachin.dao.VendingMachinDao;
import com.sg.vendingmachin.dao.VendingMachinPersistenceException;
import com.sg.vendingmachin.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Sweetlana Protsenko
 */
public class VendingMachinDaoStubImpl implements VendingMachinDao {
    
    public Item onlyItem;
    
    public VendingMachinDaoStubImpl () {
        onlyItem = new Item("1");
        onlyItem.setItemName("Snorlax");
        onlyItem.setItemPrice(BigDecimal.valueOf(0.25));
        onlyItem.setItemQuantity(2);
        
    }
    public VendingMachinDaoStubImpl(Item testItem) {
        this.onlyItem = testItem;
    }
    
    @Override
    public List<Item> getAllItems() throws VendingMachinPersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item getItem(String itemNumber) throws VendingMachinPersistenceException {
        if (itemNumber.equals(onlyItem.getItemNumber())) {
            return onlyItem;
        }else {
            return null;
        }
    }

    @Override
    public void buyItem(String itemNumber) throws VendingMachinPersistenceException {
        //do nothing
        
    }
    
}
