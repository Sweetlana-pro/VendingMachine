/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachin.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @Sweetlana Protsenko
 */
public class VendingMachinAuditDaoFileImpl implements VendingMachinAuditDao {
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException ex) {
            Logger.getLogger(VendingMachinAuditDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new VendingMachinPersistenceException("Could not persist audit information.", ex);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
        
    }
}
