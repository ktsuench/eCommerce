/*
 * The MIT License
 *
 * Copyright 2016 Kent Tsuenchy, Kureishi Shivanand, and Tejveer Jajj.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
 * @author Kent Tsuenchy
 */
public class XMLDatabaseTest {

    public XMLDatabaseTest() {
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
        XMLDatabase instance = new XMLDatabase("users.data");
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
        if (!instance.repOk()) fail("Rep invariant failed.");
    }

    /**
     * Test of writeUsers method, of class Database.
     */
    @Test
    public void testWriteUsers() {
        System.out.println("writeUsers");
        ArrayList<User> users = null;
        XMLDatabase instance = new XMLDatabase("users.data");
        // TODO: Complete write test once write method implmented
        assertTrue(instance.writeUsers(users));
    }

    /**
     * Test of closeDb method, of class XMLDatabase.
     */
    @Test
    public void testCloseDb() {
        System.out.println("closeDb");
        XMLDatabase instance = new XMLDatabase("user.data");
        assertTrue(instance.closeDb());
        if (!instance.repOk()) fail("Rep invariant failed.");
    }

    /**
     * Test of repOk method, of class XMLDatabase.
     */
    @Test
    public void testRepOk() {
        System.out.println("repOk");
        XMLDatabase instance = new XMLDatabase("users.data");
        assertTrue(instance.repOk());
    }

    /**
     * Test of toString method, of class XMLDatabase.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        XMLDatabase instance = new XMLDatabase("users.data");
        ArrayList<User> users = new ArrayList<>();

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

        users.add(bob);
        users.add(sue);
        users.add(hugo);
        
        String expResult = "Users " + users.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
        if (!instance.repOk()) fail("Rep invariant failed.");
    }

}
