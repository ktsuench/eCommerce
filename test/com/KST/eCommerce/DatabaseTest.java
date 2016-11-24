/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KST.eCommerce;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ken
 */
public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readUsers method, of class Database.
     */
    @Test
    public void testReadUsers() {
        System.out.println("readUsers");
        Database instance = new Database();
        ArrayList<User> expResult = new ArrayList<>();
        
        User bob = new Seller("bob", 1, "12345678");
        User sue = new Seller("sue", 2, "12345678");
        User hugo = new Seller("hugo", 3, "12345678");
        
        ((Seller) bob).addItem(new Item(11, "Power Bank", "16000 mAh, quick charge 2.0", 30.0));
        ((Seller) bob).addItem(new Item(12, "USB 3.0 Wire", "200 cm in length", 10.0));
        ((Seller) bob).addItem(new Item(13, "4 port USB 3.0 Hub", "Max power: 900 mAh", 17.0));
        
        ((Seller) sue).addItem(new Item(21, "Nail Polish", "Pink", 10.0));
        ((Seller) sue).addItem(new Item(22, "Beats by Dr. Dre", "High Quality", 200.0));
        ((Seller) sue).addItem(new Item(23, "iPhone 7", "Refurbished", 550.0));
        ((Seller) sue).addItem(new Item(24, "Apple Watch", "Refurbished", 525.0));
        
        ((Seller) hugo).addItem(new Item(31, "Arduino", "Starter Kit", 99.99));
        ((Seller) hugo).addItem(new Item(32, "Intel Edison", "Starter Kit", 99.99));
        
        expResult.add(bob);
        expResult.add(sue);
        expResult.add(hugo);
        
        ArrayList<User> result = instance.readUsers();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of writeUsers method, of class Database.
     */
    @Test
    public void testWriteUsers() {
        System.out.println("writeUsers");
        ArrayList<User> users = null;
        Database instance = new Database();
        instance.writeUsers(users);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
