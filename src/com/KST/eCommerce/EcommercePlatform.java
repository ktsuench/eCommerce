package com.KST.eCommerce;

import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class EcommercePlatform {

    private Session curSession;
    private PaymentProcessor checkout;
    private ArrayList<User> users;

    public EcommercePlatform() {
        Database db = new Database("users.data");

        this.curSession = new Session();
        this.checkout = new PaymentProcessor();
        this.users = db.readUsers();

        db.closeDb();
    }

    public void listItems() {

    }

    public void showInterface() {

    }
}
