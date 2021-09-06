/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.ui;
import com.sg.vendingmachin.dto.Change;
import com.sg.vendingmachin.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jesse
 */
public class VendingMachinView {

   private UserIO io;
   private Change change;

    public VendingMachinView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("===Main Menu===");
        io.print("1. Buy Item");
        io.print("2. View Item");
        io.print("3. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            if(currentItem.getItemQuantity()>0) {
                io.print(currentItem.getItemNumber() + ". "
                        + currentItem.getItemName() + " $"
                        + currentItem.getItemPrice() + " Q-ty: "
                        + currentItem.getItemQuantity());
            }else {
                io.print(" ");
            }
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayItem(Item item) {
	    if (item != null) {
	        
	        io.print(item.getItemName() + " " );
                io.print("Price, $:");
	        io.printBigDecimal(item.getItemPrice());
                io.print("Available items:");
                io.printInt(item.getItemQuantity());
	        io.print("");
	    } else {
	        io.print("No such Pokemon.");
	    }
	    io.readString("Please hit enter to continue.");
	}

    public void displayItemBanner() {
        io.print("=== Item ===");
    }

    public void displayItemBuyBanner() {
        io.print("=== Buy Item ===");
    }

    public String getItemNumberChoice() {
        return io.readString("Please enter the Item Number.");
    }

    public String displayThankYouPurchase() {
        return io.readString("Thank You for your purchase.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== All Pokemons ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public BigDecimal displayRequestDeposit(){
        String deposit = io.readString("Please put in your money");
        BigDecimal m = new BigDecimal(deposit);

        return m; 
    }

    public void displayDepositSuccessful(){
        io.print(" Deposit is successful");
    }

    public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
	}

    public void displayChange(String change) {
        io.print("Your change is $ " + change);
    }

}
