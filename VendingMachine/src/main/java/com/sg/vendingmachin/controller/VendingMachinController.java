/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.controller;

/**
 *
 * @Sweetlana Protsenko
 * 
 */
import com.sg.vendingmachin.dao.VendingMachinPersistenceException;
import com.sg.vendingmachin.dto.Item;
import com.sg.vendingmachin.service.InsufficientFundsException;
import com.sg.vendingmachin.service.NoItemInventoryException;
import com.sg.vendingmachin.service.VendingMachinServiceLayer;
import com.sg.vendingmachin.ui.UserIO;
import com.sg.vendingmachin.ui.UserIOConsoleImpl;
import com.sg.vendingmachin.ui.VendingMachinView;
import java.math.BigDecimal;
import java.util.List;


public class VendingMachinController {

    VendingMachinView view;
    VendingMachinServiceLayer service;

    //private UserIO io = new UserIOConsoleImpl();

    public VendingMachinController(VendingMachinServiceLayer service, VendingMachinView view) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            listItems();

            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        buyItem();
                        break;
                    case 2:
                        viewItem();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingMachinPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listItems()
            throws VendingMachinPersistenceException {
        view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }

    private void viewItem()
            throws VendingMachinPersistenceException {
        //view.displayItemBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
        
        String itemNumber = view.getItemNumberChoice();
        Item item = service.getItem(itemNumber);
        view.displayItem(item);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void buyItem()
            throws VendingMachinPersistenceException {

        BigDecimal deposit = requestDeposit();

        boolean hasErrors = false;
        do {
            String itemNumber = view.getItemNumberChoice();
            try {
                String purchasedItem = service.buyItem(itemNumber, deposit);
                view.displayChange(purchasedItem);
                view.displayThankYouPurchase();
                hasErrors = false;
            } catch (InsufficientFundsException | NoItemInventoryException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        // code not complete

    }

    private BigDecimal requestDeposit() {
        BigDecimal deposit = view.displayRequestDeposit();
        view.displayDepositSuccessful();
        return deposit;
    }



} 
