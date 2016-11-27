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
     * Test of login method, of class Session.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        ArrayList<User> users = new ArrayList();
        users.add(new Seller("name", 1, "pass"));
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
        users.add(new Seller("name", 1, "pass"));
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
        users.add(new Seller("name", 1, "pass"));
        String name = "name";
        String password = "pass";
        Session instance = new Session();

        try {
            boolean login = instance.login(users, name, password);
        } catch (Exception e) {
            fail("logout failed");
        }
        
        boolean logout = instance.logout();
        assertTrue(logout);
    }

    /**
     * Test of addToCart method, of class Session.
     */
    @Test
    public void testAddToCart() {
        System.out.println("addToCart");
        Item item = new Item(1, "abc", "abc", 25.3);
        Session instance = new Session();
        instance.addToCart(item);
    }

    /**
     * Test of removeFromCart method, of class Session.
     */
    @Test
    public void testRemoveFromCart() {
        System.out.println("removeFromCart");
        Item item = new Item(1, "test", "test", 10.99);
        Session instance = new Session();
        instance.addToCart(item);
        instance.removeFromCart(item);
    }

    /**
     * Test of addItemToStore method, of class Session.
     */
    @Test
    public void testAddItemToStore() {
        System.out.println("addItemToStore");
        ArrayList<User> users = new ArrayList();
        users.add(new Seller("name", 1, "pass"));
        String name = "name";
        String password = "pass";
        Item item = new Item(1, "test", "test", 10.99);
        Session instance = new Session();
        
        // User needs to be logged in first to add items to store
        try {
            instance.login(users, name, password);
            
            // Add item to store
            instance.addItemToStore(item);
            ArrayList<Item> items = instance.getItemStore();
            
            // Check that the last item in the store is the added item
            assertEquals(item, items.get(items.size()-1));
        } catch (Exception e) {
            fail("add item failed");
        }
    }

    /**
     * Test of addItemFromStore method, of class Session.
     */
    @Test
    public void testRemoveItemFromStore() {
        System.out.println("removeItemFromStore");
        ArrayList<User> users = new ArrayList();
        users.add(new Seller("name", 1, "pass"));
        String name = "name";
        String password = "pass";
        Item item = new Item(1, "test", "test", 10.99);
        Session instance = new Session();
        
        // User needs to be logged in to be able to remove items from store
        try {
            instance.login(users, name, password);
            
            // Add item first to store, then remove it
            instance.addItemToStore(item);
            assertTrue(instance.removeItemFromStore(item));
        } catch (Exception e) {
            fail("add item failed");
        }
    }
}
