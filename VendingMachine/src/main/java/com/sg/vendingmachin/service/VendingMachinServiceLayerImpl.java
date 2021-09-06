/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.service;

import com.sg.vendingmachin.dao.VendingMachinAuditDao;
import com.sg.vendingmachin.dao.VendingMachinDao;
import com.sg.vendingmachin.dao.VendingMachinPersistenceException;
import com.sg.vendingmachin.dto.Change;
import com.sg.vendingmachin.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @Sweetlana Protsenko
 */
public class VendingMachinServiceLayerImpl implements VendingMachinServiceLayer {

     

    VendingMachinAuditDao auditDao;
    VendingMachinDao dao;
    Change change;


    public VendingMachinServiceLayerImpl(VendingMachinDao dao, VendingMachinAuditDao auditDao, Change change) {
       this.dao = dao;
       this.auditDao = auditDao;
       this.change = change;
    }

    /*VendingMachinServiceLayerImpl(VendingMachinDao dao, VendingMachinAuditDao auditDao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    VendingMachinServiceLayerImpl(VendingMachinDao dao, VendingMachinAuditDao auditDao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public List<Item> getAllItems() throws VendingMachinPersistenceException {
            
       return dao.getAllItems();
    }

     @Override
    public Item getItem(String itemNumber) throws VendingMachinPersistenceException {
            
        return dao.getItem(itemNumber);
    }

    @Override
    public String buyItem(String itemNumber, BigDecimal deposit) throws
           VendingMachinPersistenceException, 
           InsufficientFundsException, 
           NoItemInventoryException {

        //Getting item
        Item item = dao.getItem(itemNumber);

        //checking if the item is available
        if(item.getItemQuantity() <= 0) {
            throw new NoItemInventoryException(
                "ERROR: " + item.getItemName()+ " is SOLD OUT");
               
        }

        //checking if deposit is greater than or equal to price
         if(deposit.compareTo(item.getItemPrice()) < 0) {
             throw new InsufficientFundsException("Insufficient Funds");
         }

        //calculating change
        change.makeChange(item, deposit);

        //subtracting items from the inventory
        dao.buyItem(itemNumber);
         
        String stringChange = change.makeChange(item, deposit) + 
               "\nYou get " + change.getQuarters()+ " Quarters "
                     +   "\n" + change.getDimes()+ " Dimes "
                      +         "\n" + change.getNickels()+ " Nickels "
                        +                "\n" + change.getPennies()+ " Pennies";

        auditDao.writeAuditEntry("Item " + item.getItemName() + " PURCHASED.");

        return stringChange;

    }

}
