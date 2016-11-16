package com.KST.eCommerce;

import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class Seller extends User implements ItemList {

    // Instance Variables
    private final String password;
    private ArrayList <Item> items; 
    /**
     * Class Constructor
     *
     * @param name
     * @param password
     */
    public Seller(String name, String password) {
        super(name, User.UserRole.seller);
        this.password = password;
        ArrayList<Item> items = new ArrayList(); 
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
    public void addItem(Item item){
        items.add(item);
    }
    
    
    @Override
    public void removeItem(Item item){ 
        items.remove(item); 
    }
    
    
    
}
