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

/**
 *
 * @author Kureishi Shivanand
 */
import java.util.ArrayList;

public class Session {

    /**
     * OVERVIEW: Sessions are mutable. Users have the options of adding/removing
     * items to their cart or adding/removing items to the store, depending on
     * whether the user is a guest or seller, respectively. Sellers need to
     * login.
     */
    
    // Abstraction function, AF(c) is
    // For user u
    // c.cart -> u.shoppingCart
    // c.isLoggedIn -> u.role == guest if false, u.role == seller if true
    
    // The rep invariant is:
    // c.user != null

    //Instance Variables
    private boolean isLoggedIn;
    private User user;
    private ItemCart cart;

    /**
     * Class Constructor
     */
    public Session() {
        // MODIFIES: isLoggedIn, user, cart
        // EFFECTS: initializes isLoggedIn to false, user as a guest, cart to new object (default)

        isLoggedIn = false;
        user = new Guest();
        cart = new ItemCart();
    }

    /**
     * Returns the current user.
     * 
     * @return User 
     */
    public User getUser() {
        // EFFECTS: returns current user
        return user;
    }

    /**
     * Checks if login information is valid and logs user in
     *
     * @param users
     * @param name
     * @param password
     * @return boolean
     * @throws java.lang.Exception
     */
    public boolean login(ArrayList<User> users, String name, String password) throws Exception {
        // REQUIRES: users != null, name != null, password != null
        // MODIFIES: users, isLoggedIn, cart
        // EFFECTS: throws Exception if there are no users or no login
        // information is provided. If their is a user, checks if the name and
        // password of that user exists in the predefined list. Then sets the 
        // role (guest/seller) as defined in the list to the current user

        // NOTE: when changing exception messages
        //       need to update test case!
        if (users == null) {                                            // if their are no User objects
            throw new Exception("No users.");
        }
        
        if (name == null || password == null) {
            throw new Exception("No login information provided.");
        }

        int i = -1;
        User tempUser;

        // if user is not logged in
        if (isLoggedIn == false) {
            while (isLoggedIn != true && ++i < users.size()) {              // and the number of login requests is less than the amount of users listed
                tempUser = (User) users.get(i);                              // tempUser assigned to current position of user in the list of users
                if (tempUser.getName().equals(name)) {                      // if the name in param MATHCES one of the name after tempUser runs through list
                    if (tempUser instanceof Seller) {
                        if (((Seller) tempUser).validPassword(password)) {   // if the password of tempUser of THAT name matches the password in param
                            isLoggedIn = true;                               // user able to log in
                            user = tempUser;                                 // set tempUser to an ACTUAL user
                            cart = null;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Logs the user out
     *
     * @return boolean
     */
    public boolean logout() {
        // REQUIRES: isLoggedIn = true
        // MODIFIES: user, isLoggedIn, cart
        // EFFECTS: logs the user out after they finish their session. Reset variables to default

        if (isLoggedIn) {
            user = new Guest();
            isLoggedIn = false;
            cart = new ItemCart();
            return true;
        }
        
        return false;
    }

    /**
     * Process the payment, and make a new Cart
     *
     * @param checkout
     * @return String
     */
    public String purchase(PaymentProcessor checkout) {
        // REQUIRES: user is not a seller, checkout != null, and size of cart > 0
        // MODIFIES: cart
        // EFFECTS: Returns checkout message at the end of session and empties the cart for next user

        String result;
        
        if (!(user instanceof Seller) && checkout != null && cart.getSizeOfCart() > 0) {
            result = checkout.processPayment();
            cart = new ItemCart();
        } else {
            result = "Failed to process payment.";
        }
        
        return result;
    }

    /**
     * Add an item to the cart
     *
     * @param item
     * @return boolean
     */
    public boolean addToCart(Item item) {
        // REQUIRES: user is not a seller, item != null, and cart != null
        // MODIFIES: cart   
        // EFFECTS: adds the specified item to the cart
        //          i.e. cart_post = cart + {item}

        if (!(user instanceof Seller) && cart != null && item != null) {
            cart.addItem(item);
            return true;
        }
        
        return false;
    }

    /**
     * Remove an item to the cart
     *
     * @param item
     * @return boolean
     */
    public boolean removeFromCart(Item item) {
        // REQUIRES: user is not a seller, cart != null, item != null, and
        //           cart contains item
        // MODIFIES: cart
        // EFFECTS: removes the specified item from the cart
        //          i.e. cart_post = cart - {item}

        if (!(user instanceof Seller) && cart != null && item != null) {
            if (cart.containsItem(item)) {
                return cart.removeItem(item);
            }
        }
        
        return false;
    }

    /**
     * Retrieve size of cart
     *
     * @return int
     */
    public int getCartSize() {
        // REQUIRES: user is not a seller and cart != null
        // EFFECTS: returns the size of the cart
        
        if (!(user instanceof Seller) && cart != null) {
            return cart.getSizeOfCart();
        }
        
        return -1;
    }

    /**
     * Retrieve cart
     *
     * @return ItemCart
     */
    public ItemCart getCart() {
        // REQUIRES: user is not a seller and cart != null
        // EFFECTS: returns the item cart
        
        if (!(user instanceof Seller) && cart != null) {
            return (ItemCart) cart.clone();
        }
        
        return null;
    }
    
    /**
     * Add the item to the store
     *
     * @param item
     * @return boolean
     */
    public boolean addItemToStore(Item item) {
        // REQUIRES: item != null user instanceof Seller
        // EFFECTS: adds the specified item to the store
        //          i.e. (Seller)user_post = (Seller)user + {item}

        if (user instanceof Seller && item != null) {
            ((Seller) user).addItem(item);
            return true;
        }
        
        return false;
    }

    /**
     * Remove the item from the store
     *
     * @param item
     * @return boolean
     */
    public boolean removeItemFromStore(Item item) {
        // REQUIRES: user instanceof Seller, item != null, and store conains item
        // MODIFIES: cart   
        // EFFECTS: removes the specified item from the store
        //          i.e. (Seller)user_post = (Seller)user - {item}

        if (user instanceof Seller && item != null) {
            Seller s = ((Seller) user);
            
            if (s.getItems().contains(item)) {
                return s.removeItem(item);
            }
        }
        
        return false;
    }

    /**
     * Retrieves the items of the seller
     *
     * @return ArrayList&lt;Item&gt;
     */
    public ArrayList<Item> getItemStore() {
        // REQUIRES: user instanceof Seller
        // EFFECTS: returns the items of the seller if user is a seller
        //          otherwise returns null

        if (user instanceof Seller) {
            return ((Seller) user).getItems();
        }
        
        return null;
    }

    /**
     * Rep-OK Function for Representation
     *
     * @return boolean
     */
    public boolean repOK() {
        //EFFECTS: Returns true if the rep invariant holds for this,
        //otherwise it returns false.
        return user != null;
    }

    /**
     * toString for Abstract Function
     *
     * @return String
     */
    @Override
    public String toString() {
        //EFFECTS: Returns the string representation of the abstraction. 
        if (repOK() == true) {
            String result = "User: " + user.getRole().toString();
            
            for(Item i : cart.getItems()) {
                result += "\n" + i.toString();
            }
            
            return result;
        } else {
            return "Invalid rep invariant.";
        }
    }
}
