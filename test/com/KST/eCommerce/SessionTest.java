package com.KST.eCommerce;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

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

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private Session instanceLogin(Session instance) {
        ArrayList<User> users = new ArrayList();
        users.add(new Seller("name", 1, "pass"));
        String name = "name";
        String password = "pass";
        
        try {
            instance.login(users, name, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Failed to login for Session instance.");
        }
        
        return instance;
    }
    
    /**
     * Test of getUser method, of class Session.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Session instance = new Session();
        
        // Case 1: user instanceof Guest
        assertEquals(new Guest(), (Guest) instance.getUser());
        
        // Login
        instance = this.instanceLogin(instance);
        
        // Case 2: user instanceof Seller
        assertEquals(new Seller("name", 1, "pass"), (Seller) instance.getUser());
    }
    
    /**
     * Test of login (false) method, of class Session.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        Session instance = new Session();
        ArrayList<User> users = new ArrayList();
        users.add(new Seller("name", 1, "pass"));

        try {     
            // Case 1: user exists and password is correct, users != null
            assertTrue(instance.login(users, "name", "pass"));
            if(!instance.repOK()) fail("Rep invariant failed.");
            
            // Case 2: user exists and password is incorrect, users != null
            assertFalse(instance.login(users, "name", "password"));
            if(!instance.repOK()) fail("Rep invariant failed.");
            
            // Case 3: user does not exist, users != null
            assertFalse(instance.login(users, "user", "password"));
            if(!instance.repOK()) fail("Rep invariant failed.");
            
            // Case 4: users == null, produces exception
            instance.login(null, "user", "password");
            if(!instance.repOK()) fail("Rep invariant failed.");
            
            // Case 5: name == null, produces exception
            instance.login(users, null, "password");
            if(!instance.repOK()) fail("Rep invariant failed.");
            
            // Case 6: password == null, produces exception
            instance.login(users, "user", null);
        } catch (Exception e) {
            if(!instance.repOK()) fail("Rep invariant failed.");
            
            // Case 4, 5, and 6 continued
            String case4 = "No users.";
            String case5And6 = "No login information provided.";
            String errMsg = e.getMessage();
            
            if (!errMsg.equals(case4) && !errMsg.equals(case5And6)) {
                fail("Login test case failed.");
            }
        }
    }

    /**
     * Test of logout (false) method, of class Session.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        Session instance = new Session();
        
        // Case 1: user not logged in
        assertFalse(instance.logout());
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Login
        instance = this.instanceLogin(instance);
        
        // Case 2: user logged in
        assertTrue(instance.logout());
        if(!instance.repOK()) fail("Rep invariant failed.");
    }
    
    /**
     * Test of purchase method, of class Session.
     */
    @Test
    public void testPurchase() {
        System.out.println("purchase");
        Session instance;
        PaymentProcessor checkout = new PaymentProcessor();
        String failProcessing = "Failed to process payment.";
        String passProcessing = "Payment recieved.";

        // Case 1: user is not a seller, checkout == null and cart is empty
        instance = new Session();
        
        assertEquals(failProcessing, instance.purchase(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 2: user is not a seller, checkout == null and cart is not empty
        instance = new Session();
        instance.addToCart(new Item(11, "test", "test", 10.99));
        
        assertEquals(failProcessing, instance.purchase(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 3: user is not a seller, checkout != null and cart is empty
        instance = new Session();
        
        assertEquals(failProcessing, instance.purchase(checkout));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 4 user is not a seller, checkout != null and cart not empty
        instance = new Session();
        instance.addToCart(new Item(11, "test", "test", 10.99));

        assertEquals(passProcessing, instance.purchase(checkout));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 5: user is a seller, checkout == null and cart is empty
        instance = this.instanceLogin(new Session());

        assertEquals(failProcessing, instance.purchase(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 6: user is a seller, checkout == null and cart is not empty
        instance = this.instanceLogin(new Session());
        instance.addToCart(new Item(11, "test", "test", 10.99));
        
        assertEquals(failProcessing, instance.purchase(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 7: user is a seller, checkout != null and cart is empty
        instance = this.instanceLogin(new Session());
        
        assertEquals(failProcessing, instance.purchase(checkout));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 8 user is a seller, checkout != null and cart not empty
        instance = this.instanceLogin(new Session());
        instance.addToCart(new Item(11, "test", "test", 10.99));
        
        assertEquals(failProcessing, instance.purchase(checkout));
        if(!instance.repOK()) fail("Rep invariant failed.");
    }
    
    /**
     * Test of addToCart method, of class Session.
     */
    @Test
    public void testAddToCart() {
        System.out.println("addToCart");
        Session instance = new Session();
        Item item = new Item(1, "test", "test", 10.99);
        
        // Case 1: user is a guest, item != null
        instance.addToCart(item);
        ItemCart cart  = instance.getCart();

        assertEquals(item, cart.getItems().get(cart.getSizeOfCart()-1));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 2: user is a guest, item == null
        assertFalse(instance.addToCart(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Login
        instance = this.instanceLogin(instance);
        
        // Case 3: user is not a guest, item != null
        assertFalse(instance.addToCart(item));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 4: user is not a guest, item == null
        assertFalse(instance.addToCart(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
    }

    /**
     * Test of removeFromCart method, of class Session.
     */
    @Test
    public void testRemoveFromCart() {
        System.out.println("removeFromCart");
        Session instance = new Session();
        Item item = new Item(1, "test", "test", 10.99);
        
        // Case 1: user is a guest, item == null, cart != null
        assertFalse(instance.removeFromCart(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 2: user is a guest, item != null, cart != null, cart does not contain item
        assertFalse(instance.removeFromCart(item));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 3: user is a guest, item != null, cart != null, cart contains item
        instance.addToCart(item);
        
        assertTrue(instance.removeFromCart(item));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Login
        instance = this.instanceLogin(new Session());
        
        // Case 4: user is not a guest, item == null, cart == null
        assertFalse(instance.removeFromCart(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 5: user is not a guest, item != null, cart == null
        assertFalse(instance.removeFromCart(item));
        if(!instance.repOK()) fail("Rep invariant failed.");
    }

    /**
     * Test of getCartSize method, of class Session.
     */
    @Test
    public void testGetCartSize() {
        System.out.println("getCartSize");
        Session instance = new Session();
        
        // Case 1: user is a guest && cart != null
        assertEquals(0, instance.getCartSize());
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Login
        instance = this.instanceLogin(instance);
            
        // Case 2: user is not a guest && cart != null
        assertEquals(-1, instance.getCartSize());
        if(!instance.repOK()) fail("Rep invariant failed.");
    }

    /**
     * Test of getCart method, of class Session.
     */
    @Test
    public void testGetCart() {
        System.out.println("getCart");
        Session instance = new Session();
        
        // Case 1: user is a guest && cart != null
        assertEquals(new ItemCart(), instance.getCart());
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Login
        instance = this.instanceLogin(instance);
        
        // Case 2: user is not a guest && cart != null
        assertEquals(null, instance.getCart());
        if(!instance.repOK()) fail("Rep invariant failed.");
    }
    
    /**
     * Test of addItemToStore method, of class Session.
     */
    @Test
    public void testAddItemToStore() {
        System.out.println("addItemToStore");
        Session instance = new Session();
        Item item = new Item(1, "test", "test", 10.99);
        
        // Case 1: user is not a seller, item != null
        assertFalse(instance.addItemToStore(item));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 2: user is not a seller, item == null
        assertFalse(instance.addItemToStore(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Login
        instance = this.instanceLogin(instance);
        
        // Case 3: user is a seller, item != null
        {
            // Add item to store
            instance.addItemToStore(item);
            ArrayList<Item> items = instance.getItemStore();

            // Check that the last item in the store is the added item
            assertEquals(item, items.get(items.size()-1));
            if(!instance.repOK()) fail("Rep invariant failed.");
        }
            
        // Case 4: user is a seller, item == null
        assertFalse(instance.addItemToStore(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
    }

    /**
     * Test of addItemFromStore method, of class Session.
     */
    @Test
    public void testRemoveItemFromStore() {       
        System.out.println("removeItemFromStore");
        Session instance = new Session();
        Item item = new Item(1, "test", "test", 10.99);
        
        // Case 1: user is not a seller, item == null
        assertFalse(instance.removeItemFromStore(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 2: user is not a seller, item != null
        assertFalse(instance.removeItemFromStore(item));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Login
        instance = this.instanceLogin(instance);
        
        // Case 3: user is a seller, item == null
        assertFalse(instance.removeItemFromStore(null));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 3: user is a seller, item != null, store does not contain item
        assertFalse(instance.removeItemFromStore(item));
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 3: user is a seller, item != null, store does contain item
        instance.addItemToStore(item);
        
        assertTrue(instance.removeItemFromStore(item));
        if(!instance.repOK()) fail("Rep invariant failed.");
    }

    /**
     * Test of getItemStore method, of class Session.
     */
    @Test
    public void testGetItemStore() {
        System.out.println("getItemStore");
        Session instance = new Session();
        
        // Case 1: user is not a seller
        assertEquals(null, instance.getItemStore());
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Login
        instance = this.instanceLogin(instance);
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 2: user is a seller and store is empty
        assertEquals(new ArrayList<Item>(), instance.getItemStore());
        if(!instance.repOK()) fail("Rep invariant failed.");
        
        // Case 3: user is a seller and store is not empty
        Item item = new Item(1, "test", "test", 10.99);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        
        instance.addItemToStore(item);
        
        assertEquals(items, instance.getItemStore());
        if(!instance.repOK()) fail("Rep invariant failed.");
    }

    /**
     * Test of repOK method, of class Session.
     */
    @Test
    public void testRepOK() {
        System.out.println("repOK");
        Session instance = new Session();
        
        assertEquals(true, instance.repOK());
    }

    /**
     * Test of toString method, of class Session.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Session instance = new Session();
        
        assertEquals("User: " + User.UserRole.guest, instance.toString());
        if(!instance.repOK()) fail("Rep invariant failed.");
    }
}
