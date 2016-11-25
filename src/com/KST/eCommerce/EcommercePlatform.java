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

    public ArrayList<Item> listItems() {
        ArrayList<Item> items = new ArrayList<>();

        for (User u : users) {
            for (Item i : ((Seller) u).getItems()) {
                items.add(i);
            }
        }

        return (ArrayList<Item>) items.clone();
    }

    public void showInterface() {
        EcommerceGUI guiInterface = new EcommerceGUI();

        guiInterface.showGUI(this.listItems());
    }

    public static void main(String[] args) {
        EcommercePlatform platform = new EcommercePlatform();
        
        platform.showInterface();
    }
}
