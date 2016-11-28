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
     * @return ArrayList&lt;Item&gt;
     */
    public ArrayList<Item> viewStore() {
        ArrayList<Item> items = new ArrayList<>();

        for (User u : users) {
            if (u instanceof Seller) {
                for (Item i : ((Seller) u).getItems()) {
                    items.add(i);
                }
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
