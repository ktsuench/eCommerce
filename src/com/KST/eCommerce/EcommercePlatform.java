package com.KST.eCommerce;

import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class EcommercePlatform {

    private final Session session;
    private final PaymentProcessor checkout;
    private final ArrayList<User> users;

    public EcommercePlatform() {
        Database db = new Database("users.data");

        this.session = new Session();
        this.checkout = new PaymentProcessor();
        this.users = db.readUsers();

        db.closeDb();
    }

    public ArrayList<Item> listItems() {
        ArrayList<Item> items = new ArrayList<>();

        for (User u : users) {
            for (Item i : ((Seller) u).getItems()) {
                items.add(i);
            }
        }

        return (ArrayList<Item>) items.clone();
    }

    public Session getSession() {
        return this.session;
    }
    
    public PaymentProcessor getPaymentProcessor() {
        return this.checkout;
    }
    
    public void showInterface() {
        EcommerceGUI.showGUI(this);
    }

    public static void main(String[] args) {
        EcommercePlatform platform = new EcommercePlatform();
        
        platform.showInterface();
    }
}
