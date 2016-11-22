package com.KST.eCommerce;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kureishi Shivanand
 */
public class SessionTest {

    public SessionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of Session method, of class Session.
     */
    @Test
    public void testSession() {
        System.out.println("Session");
        Session instance = new Session();
        instance.Session();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class Session.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        ArrayList<User> users = new ArrayList();
        users.add(new Seller("name", "pass"));
        String name = "Name";
        String password = "Password";
        Session instance = new Session();

        try {
            boolean login = instance.login(users, name, password);
            assertFalse(login);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("login failed");
        }
    }

    /**
     * Test of login method, of class Session.
     */
    @Test
    public void testTrueLogin() {
        System.out.println("login");
        ArrayList<User> users = new ArrayList();
        users.add(new Seller("name", "pass"));
        String name = "name";
        String password = "pass";
        Session instance = new Session();

        try {
            boolean login = instance.login(users, name, password);
            assertTrue(login);
        } catch (Exception e) {
            fail("login failed");
        }
    }

    /**
     * Test of logout method, of class Session.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        Session instance = new Session();
        boolean logout = instance.logout();
        assertFalse(logout);
    }

    /**
     * Test of logout method, of class Session.
     */
    @Test
    public void testTrueLogout() {
        System.out.println("logout");
        ArrayList<User> users = new ArrayList();
        users.add(new Seller("name", "pass"));
        String name = "name";
        String password = "pass";
        Session instance = new Session();

        try {
            boolean login = instance.login(users, name, password);
        } catch (Exception e) {
            fail("login failed");
        }

        boolean logout = instance.logout();
        assertTrue(logout);
    }

    /**
     * Test of purchase method, of class Session.
     */
    /*@Test
    public void testPurchase() {
        System.out.println("purchase");
        PaymentProcessor checkout = null;
        Session instance = new Session();
        instance.purchase(checkout);
    }*/
    /**
     * Test of addToCart method, of class Session.
     */
    @Test
    public void testAddToCart() {
        System.out.println("addToCart");
        Item item = null;
        Session instance = new Session();
        instance.addToCart(item);
    }

    /**
     * Test of removeFromCart method, of class Session.
     */
    @Test
    public void testRemoveFromCart() {
        System.out.println("removeFromCart");
        Item item = null;
        Session instance = new Session();
        instance.removeFromCart(item);
    }

    /**
     * Test of addItemToStore method, of class Session.
     */
    @Test
    public void testAddItemToStore() {
        System.out.println("addItemToStore");
        Item item = null;
        Session instance = new Session();
        instance.addItemToStore(item);
    }

    /**
     * Test of addItemFromStore method, of class Session.
     */
    @Test
    public void testAddItemFromStore() {
        System.out.println("addItemFromStore");
        Item item = null;
        Session instance = new Session();
        instance.addItemFromStore(item);
    }
}
