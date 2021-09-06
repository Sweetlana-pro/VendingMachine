/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin;

import com.sg.vendingmachin.controller.VendingMachinController;
import com.sg.vendingmachin.dao.VendingMachinAuditDao;
import com.sg.vendingmachin.dao.VendingMachinAuditDaoFileImpl;
import com.sg.vendingmachin.dao.VendingMachinDao;
import com.sg.vendingmachin.dao.VendingMachinDaoFileImpl;
import com.sg.vendingmachin.dto.Change;
import com.sg.vendingmachin.service.VendingMachinServiceLayer;
import com.sg.vendingmachin.service.VendingMachinServiceLayerImpl;
import com.sg.vendingmachin.ui.UserIO;
import com.sg.vendingmachin.ui.UserIOConsoleImpl;
import com.sg.vendingmachin.ui.VendingMachinView;

/**
 *
 * @Sweetlana Protsenko
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachinView myView = new VendingMachinView(myIo);
        VendingMachinDao myDao = new VendingMachinDaoFileImpl();
        VendingMachinAuditDao myAuditDao = new VendingMachinAuditDaoFileImpl();
        Change myChange = new Change();
        VendingMachinServiceLayer myService = new VendingMachinServiceLayerImpl(myDao, myAuditDao, myChange);
        VendingMachinController controller
                = new VendingMachinController(myService, myView);
        controller.run();
    }
}
