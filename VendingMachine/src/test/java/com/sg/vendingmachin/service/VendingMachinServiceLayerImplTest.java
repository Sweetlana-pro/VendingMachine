/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.service;

import com.sg.vendingmachin.dao.VendingMachinAuditDao;
import com.sg.vendingmachin.dao.VendingMachinAuditDaoFileImpl;
import com.sg.vendingmachin.dao.VendingMachinDao;
import com.sg.vendingmachin.dao.VendingMachinDaoFileImpl;
import com.sg.vendingmachin.dao.VendingMachinPersistenceException;
import com.sg.vendingmachin.dto.Change;
import com.sg.vendingmachin.dto.Item;
import java.io.File;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @Sweetlana Protsenko
 */
public class VendingMachinServiceLayerImplTest {
    
    private VendingMachinServiceLayer service;
    
    public VendingMachinServiceLayerImplTest() {
        VendingMachinDao dao = new VendingMachinDaoFileImpl("testinventory.txt");
        File file = new File("testinventory.txt");
        
        VendingMachinAuditDao auditDao = new VendingMachinAuditDaoFileImpl(); 
        Change change = new Change();
        service = new VendingMachinServiceLayerImpl(dao, auditDao, change);
    }
    
    @Test
    public void testGetAllItems() throws Exception {
        //Arrange
        Item testFirstClone = new Item("1");
        testFirstClone.setItemName("Snorlax");
        testFirstClone.setItemPrice(BigDecimal.valueOf(0.25));
        testFirstClone.setItemQuantity(Integer.parseInt("2"));
        
        Item testSecondClone = new Item("2");
        testSecondClone.setItemName("Picachu");
        testSecondClone.setItemPrice(BigDecimal.valueOf(0.45));
        testSecondClone.setItemQuantity(Integer.parseInt("2"));
        
        Item testThirdClone = new Item("3");
        testThirdClone.setItemName("Eevee");
        BigDecimal itemPrice = new BigDecimal ("0.50");
        String i = String.valueOf(itemPrice);
        testThirdClone.setItemPrice(itemPrice);
        testThirdClone.setItemQuantity(Integer.parseInt("1"));
        
        Item testFourthClone = new Item("4");
        testFourthClone.setItemName("Mewtwo");
        BigDecimal fourthItemPrice = new BigDecimal ("0.60");
        String b = String.valueOf(fourthItemPrice);
        testFourthClone.setItemPrice (fourthItemPrice);        
        testFourthClone.setItemQuantity(Integer.parseInt("2"));
        
        //Act&Assert
        assertEquals(4, service.getAllItems().size(),
                "Should have 4 items.");
        assertTrue(service.getAllItems().contains(testFirstClone),
                "One of the items should be Snorlax");
        assertTrue(service.getAllItems().contains(testSecondClone),
                "One of the items should be Picachu");
        assertTrue(service.getAllItems().contains(testThirdClone),
                "One of the items should be Eevee");
        assertTrue(service.getAllItems().contains(testFourthClone),
                "One of the items should be Mewtwo");
        
    }
    
    @Test
    public void testGetItem()throws Exception{
        //ARRANGE
        Item testClone = new Item ("1");
        testClone.setItemName("Snorlax");
        testClone.setItemPrice(BigDecimal.valueOf(0.25));
        testClone.setItemQuantity(2);
        
        Item testSecondClone = new Item("2");
        testSecondClone.setItemName("Picachu");
        testSecondClone.setItemPrice(BigDecimal.valueOf(0.45));
        testSecondClone.setItemQuantity(Integer.parseInt("2"));
        
        Item testThirdClone = new Item("3");
        testThirdClone.setItemName("Eevee");
        BigDecimal itemPrice = new BigDecimal ("0.50");
        String i = String.valueOf(itemPrice);
        testThirdClone.setItemPrice(itemPrice);
        testThirdClone.setItemQuantity(Integer.parseInt("0"));
        
        Item testFourthClone = new Item("4");
        testFourthClone.setItemName("Mewtwo");
        BigDecimal fourthItemPrice = new BigDecimal ("0.60");
        String b = String.valueOf(fourthItemPrice);
        testFourthClone.setItemPrice (fourthItemPrice);        
        testFourthClone.setItemQuantity(Integer.parseInt("2"));
        
        //ACT
        Item shouldBeSnorlax = service.getItem("1");
        Item shouldBePicachu = service.getItem("2");
        Item shouldBeEevee = service.getItem("3");
        Item shouldBeMewtwo = service.getItem("4");
        
        //ASSERT
        assertNotNull(shouldBeSnorlax, "Getting 1 should be not null");
        assertEquals(testClone, shouldBeSnorlax, 
                "Item stored under 1 should be Snorlax");
        
        assertNotNull(shouldBePicachu, "Getting 1 should be not null");
        assertEquals(testSecondClone, shouldBePicachu, 
                "Item stored under 1 should be Picachu");
        
        assertNotNull(shouldBeEevee, "Getting 1 should be not null");
        assertEquals(testThirdClone, shouldBeEevee, 
                "Item stored under 1 should be Eevee");
        
        assertNotNull(shouldBeMewtwo, "Getting 1 should be not null");
        assertEquals(testFourthClone, shouldBeMewtwo, 
                "Item stored under 1 should be Mewtwo");
        
        Item shouldBeNull = service.getItem("42");
        assertNull(shouldBeNull, "Getting 42 should be null.");
        
    }
    
    @Test
    public void testBuyItem() throws Exception {
        
        //ARRANGE
        Item testClone = new Item ("1");
        testClone.setItemName("Snorlax");
        testClone.setItemPrice(BigDecimal.valueOf(0.25));
        testClone.setItemQuantity(2);
        
        Item testSecondClone = new Item("2");
        testSecondClone.setItemName("Picachu");
        testSecondClone.setItemPrice(BigDecimal.valueOf(0.45));
        testSecondClone.setItemQuantity(Integer.parseInt("2"));
        
        //ACT
        String buyItem = service.buyItem("1", new BigDecimal("1.00"));
        
        String buySecondItem = service.buyItem("2", new BigDecimal("2.00"));
        
        
        //ASSERT
        //assertEquals("0.75  \nYou get 3 Quarters \n0 Dimes \n0 Nickels \n0 Pennies", buyItem);
        //assertEquals("1.55  \nYou get 6 Quarters \n        0 Dimes\n        1 Nickels \n        0 Pennies", buySecondItem);
        assertNotNull(buyItem, "Change should not be null");
      
    }
    @Test
    public void testBuyItemInsufficientFundsException() throws Exception {
        try {
            service.buyItem("1", new BigDecimal("0.10"));
        } catch (InsufficientFundsException e) {
            
        }
    }
    
    @Test
    public void testNoInventoryExp() throws VendingMachinPersistenceException, InsufficientFundsException {
    try {
        service.buyItem("3", new BigDecimal("3.00"));
    } catch(NoItemInventoryException e) {
    }
}
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /*@Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
    }*/
    
}
