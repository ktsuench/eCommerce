package com.KST.eCommerce;

import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class Seller extends User implements ItemList {

    // Instance Variables
    private final int id;
    private final String password;
    private final ArrayList<Item> items;

    /**
     * Class Constructor
     *
     * @param name
     * @param id
     * @param password
     */
    public Seller(String name, int id, String password) {
        super(name, User.UserRole.seller);
        this.id = id;
        this.password = password;
        this.items = new ArrayList();
    }

    /**
     * Returns user ID
     *
     * @return int
     */
    public int getId() {
        return this.id;
    }

    /**
     * Validates that password is correct.
     *
     * @param password
     * @return boolean
     */
    public boolean validPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof Seller) {
            Seller s = (Seller) o;

            if (s.items.size() == this.items.size()) {
                for (int i = 0; i < s.items.size(); i++) {
                    if (!s.items.get(i).equals(this.items.get(i))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
