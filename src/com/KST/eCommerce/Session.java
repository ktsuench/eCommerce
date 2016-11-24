package com.KST.eCommerce;

/**
 *
 * @author Kureishi Shivanand
 */
import java.util.ArrayList;

public class Session {

    // Instance Variables
    private boolean isLoggedIn;
    private User user;
    private ItemCart cart;

    /**
     * Constructor
     */
    public void Session() {

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
     * @throws java.lang.Exception
     */
    public boolean login(ArrayList<User> users, String name, String password) throws Exception {

        if (users == null) {
            throw new Exception("Users is null");
        }

        int i = -1;
        User tempUser;

        // if user is not logged in
        if (isLoggedIn == false) {
            while (isLoggedIn != true && ++i < users.size()) {            // and the number of login requests is less than the amount of users listed
                tempUser = (User) users.get(i);                          // tempUser assigned to current position of user in the list of users
                if (tempUser.getName().equals(name)) {                   // if the name in param MATHCES one of the name after tempUser runs through list
                    if (((Seller) tempUser).validPassword(password)) {    // if the password of tempUser of THAT name matches the password in param
                        isLoggedIn = true;                              // user able to log in
                        user = tempUser;                                // set tempUser to an ACTUAL user
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Logs the user out
     */
    public boolean logout() {
        if (isLoggedIn) {
            user = null;
            isLoggedIn = false;
            return true;
        }
        return false;
    }

    /**
     * Process the payment, and make a new Cart
     *
     * @param checkout
     */
    public void purchase(PaymentProcessor checkout) {
        checkout.processPayment();
        cart = new ItemCart();
    }

    /**
     * Add an item to the cart
     *
     * @param item
     */
    public void addToCart(Item item) {
        cart.addItem(item);
    }

    /**
     * Remove an item to the cart
     *
     * @param item
     */
    public void removeFromCart(Item item) {
        cart.removeItem(item);
    }

    /**
     * Add the idem to the store
     *
     * @param item
     */
    public void addItemToStore(Item item) {
        ((Seller) user).addItem(item);
    }

    /**
     * Remove the idem from the store
     *
     * @param item
     */
    public void addItemFromStore(Item item) {
        ((Seller) user).removeItem(item);
    }
}
