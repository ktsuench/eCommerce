package com.KST.eCommerce;
/**
 *
 * @author Kureishi Shivanand
 */
import java.util.ArrayList;

public class Session{
    
    // Instance Variables
    private boolean isLoggedIn ;
    private User user;
    private ItemCart cart;
    
    /**
     * Constructor
     */
    public void Session () {
        
        isLoggedIn = false;
        user = null;
        cart = new ItemCart();
    }
    
    /**
     * Checks if login information is valid and logs user in
     * 
     * @param users
     * @param name
     * @param password 
     */
    public void login (ArrayList<User> users, String name, String password) {
        
        int i = 0;
        User tempUser;
        
        if (isLoggedIn == false) {
            while (isLoggedIn != true && i < users.size()) {
                tempUser = (User)users.get(i);
                if(tempUser.getName().equals(name)) {
                    if(((Seller)tempUser).validPassword(password)) {
                        isLoggedIn = true;
                        user = tempUser;
                    }
                }
            }
        } 
    }
    
    /**
     * Logs the user out
     */
    public void logout () {
        user = null;
        isLoggedIn = false;
    }
    
    /**
     * 
     * @param checkout 
     */
    public void purchase (PaymentProcessor checkout) {
        checkout.processPayment();
        cart = new ItemCart();
    }
    
    public void addToCart (Item item) {
        cart.addItem(item);
    }
    
    public void removeFromCart (Item item) {
        cart.removeItem(item);
    }
    
    public void addItemToStore (Item item) {
        ((Seller)user).addItem(item);
    }
    
    public void addItemFromStore (Item item) {
        ((Seller)user).removeItem(item);
    }
}
