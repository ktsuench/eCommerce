package com.KST.eCommerce;

/**
 *
 * @author Kureishi Shivanand
 */
import java.util.ArrayList;

public class Session {

    /**
     * OVERVIEW: Sessions are mutable. When a user is able to login, they have
     * the options of adding/removing items to their cart or adding/removing
     * items to the store, depending on whether the user is a guest or seller,
     * respectively.
     */
    
    // Abstraction function is
    //          c.cart = cart
    
    // The rep invariant is:
    // c.cart != null
    
//Instance Variables
    private boolean isLoggedIn;
    private User user;
    private ItemCart cart;

    /**
     * Constructor
     */
    public Session() {
        // MODIFIES: isLoggedIn, user, cart
        // EFFECTS: initializes isLoggedIn to false, user as a null array, cart to new object (default)

        isLoggedIn = false;
        user = null;
        cart = new ItemCart();
    }

    public User getUser() {
        return ((Seller) user);
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
        // MODIFIES: users, isLoggedIn
        // EFFECTS: throws Exception if there are no users. If their is a user, checks if 
        // the name and password of that user exists in the predefined list. Then sets the 
        // role (guest/seller) as defined in the list to the current user

        if (users == null) {                                            // if their are no User objects
            throw new Exception("No users");
        }

        int i = -1;
        User tempUser;

        // if user is not logged in
        if (isLoggedIn == false) {
            while (isLoggedIn != true && ++i < users.size()) {           // and the number of login requests is less than the amount of users listed
                tempUser = (User) users.get(i);                          // tempUser assigned to current position of user in the list of users
                if (tempUser.getName().equals(name)) {                   // if the name in param MATHCES one of the name after tempUser runs through list
                    if (((Seller) tempUser).validPassword(password)) {   // if the password of tempUser of THAT name matches the password in param
                        isLoggedIn = true;                               // user able to log in
                        user = tempUser;                                 // set tempUser to an ACTUAL user
                        return true;
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
        // REQUIRES: user != null
        // MODIFIES: user, isLoggedIn
        // EFFECTS: logs the user out after they finish their session. Reset variables to default

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
     * @return String
     */
    public String purchase(PaymentProcessor checkout) {
        // MODIFIES: cart
        // EFFECTS: Returns checkout message at the end of session and empties the cart for next user

        String result = checkout.processPayment();
        cart = new ItemCart();

        return result;
    }

    /**
     * Add an item to the cart
     *
     * @param item
     */
    public void addToCart(Item item) {
        // REQUIRES: item != null
        // MODIFIES: cart   
        // EFFECTS: adds the specified item to the cart
        //          i.e. cart_post = cart + {item}

        cart.addItem(item);
    }

    /**
     * Remove an item to the cart
     *
     * @param item
     * @return boolean
     */
    public void removeFromCart(Item item) {
        // REQUIRES: item != null
        // MODIFIES: cart
        // EFFECTS: removes the specified item from the cart
        //          i.e. cart_post = cart - {item}

        cart.removeItem(item);
    }

    /**
     * Retrieve size of cart
     *
     * @return int
     */
    public int getCartSize() {
        // EFFECTS: returns the size of the cart
        return cart.getSizeOfCart();
    }

    /**
     * Retrieve cart
     *
     * @return ItemCart
     */
    public ItemCart getCart() {
        // EFFECTS: returns the item cart
        return (ItemCart) cart.clone();
    }

    /**
     * Add the idem to the store
     *
     * @param item
     * @return boolean
     */
    public boolean addItemToStore(Item item) {
        // REQUIRES: item != null
        // MODIFIES: cart   
        // EFFECTS: adds the specified item to the store
        //          i.e. (Seller)user_post = (Seller)user + {item}
        
        if (isLoggedIn == true) {
            ((Seller) user).addItem(item);
            return true;
        }
        return false;
    }

    /**
     * Remove the idem from the store
     *
     * @param item
     * @return boolean
     */
    public boolean removeItemFromStore(Item item) {
        // REQUIRES: user != null
        // MODIFIES: cart   
        // EFFECTS: removes the specified item from the store
        //          i.e. (Seller)user_post = (Seller)user - {item}

        if(item == null){
            return false;
        } else {
            ((Seller) user).removeItem(item);
            return true;
        }
    }

    /**
     * Retrieves the items of the seller
     *
     * @return ArrayList
     */
    public ArrayList<Item> getItemStore() {
        // REQUIRES: user != null
        // EFFECTS: returns the items of the seller
        
        if(user != null) {
            return ((Seller) user).getItems();
        } else {
            return null;
        }
    }

    /**
     * Rep-OK Function for Representation
     *
     * @return boolean
     */
    public boolean repOK() {

        if (user == null) {
            return false;
        }
        return true;
    }

    /**
     * toString for Abstract Function
     *
     * @return String
     */
    @Override
    public String toString() {
        if (repOK() == true) {
            return "Valid";
        }
        else {
            return "Invalid";
        }
    }
}
