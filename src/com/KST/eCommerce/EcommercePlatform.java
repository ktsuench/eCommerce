package com.KST.eCommerce;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kent Tsuenchy
 */
public class EcommercePlatform {

    // Instance Variables
    private final Session session;
    private final PaymentProcessor checkout;
    private final ArrayList<User> users;

    /**
     * Class Constructor
     */
    public EcommercePlatform() {
        Database db = new Database("users.data");

        this.session = new Session();
        this.checkout = new PaymentProcessor();
        this.users = db.readUsers();

        db.closeDb();
    }

    /**
     * Retrieve the list of store items
     *
     * @return ArrayList<Item>
     */
    public ArrayList<Item> listItems() {
        ArrayList<Item> items = new ArrayList<>();

        for (User u : users) {
            for (Item i : ((Seller) u).getItems()) {
                items.add(i);
            }
        }

        return (ArrayList<Item>) items.clone();
    }

    /**
     * Retrieve current session
     *
     * @return Session
     */
    public Session getSession() {
        return this.session;
    }

    /**
     * Tries to login with given name and password. On failure returns false
     * otherwise returns true.
     *
     * @param name
     * @param password
     * @return boolean
     */
    public boolean login(String name, String password) {
        try {
            return this.session.login(this.users, name, password);
        } catch (Exception ex) {
            Logger.getLogger(EcommercePlatform.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Retrieves the payment processor.
     * @return 
     */
    public PaymentProcessor getPaymentProcessor() {
        return this.checkout;
    }

    /**
     * Shows the GUI interface for the application.
     */
    public void showInterface() {
        EcommerceGUI.showGUI(this);
    }

    public static void main(String[] args) {
        EcommercePlatform platform = new EcommercePlatform();

        platform.showInterface();
    }
}
