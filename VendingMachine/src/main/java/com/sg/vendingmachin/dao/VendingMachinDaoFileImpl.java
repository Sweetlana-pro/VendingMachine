/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.dao;

import com.sg.vendingmachin.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @Sweetlana Protsenko
 */
public class VendingMachinDaoFileImpl implements VendingMachinDao {

    public static String INVENTORY_FILE;
    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    public VendingMachinDaoFileImpl() {
        INVENTORY_FILE = "inventory.txt";
    }

    public VendingMachinDaoFileImpl(String inventoryTextFile) {
        INVENTORY_FILE = inventoryTextFile;
    }


    @Override
    public List<Item> getAllItems()
            throws VendingMachinPersistenceException {
        loadInventory();
        return new ArrayList<>(items.values());
    }

    @Override
    public Item getItem(String itemNumber)
            throws VendingMachinPersistenceException {
        loadInventory();
        return items.get(itemNumber);
    }

    @Override
    public void buyItem(String itemNumber) 
        throws VendingMachinPersistenceException {

       Item item = getItem(itemNumber);
       item.buyItem();

        writeInventory();

    }

    private void loadInventory() throws VendingMachinPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinPersistenceException(
                    "-_- Could not load reciept data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemPrice(new BigDecimal(currentTokens[2]));
            currentItem.setItemQuantity(Integer.parseInt(currentTokens[3]));

            items.put(currentItem.getItemNumber(), currentItem);
        }

        scanner.close();
    }

    private void writeInventory() throws VendingMachinPersistenceException {

	    PrintWriter out;

	    try {
	        out = new PrintWriter(new FileWriter(INVENTORY_FILE));
	    } catch (IOException e) {
	        throw new VendingMachinPersistenceException(
	                "Could not save reciept data.", e);
	    }


	    List<Item> itemList = this.getAllItems();
	    for (Item currentItem : itemList) {

	        out.println(currentItem.getItemNumber() + DELIMITER
	                + currentItem.getItemName() + DELIMITER
                        + currentItem.getItemPrice() + DELIMITER
	                + currentItem.getItemQuantity());

	        out.flush();
	    }

	    out.close();
	}


}

