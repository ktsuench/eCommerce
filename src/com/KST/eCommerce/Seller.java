package com.KST.eCommerce;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kent Tsuenchy
 */
public class Seller extends User implements ItemList {

    //OVERVIEW: Creates an objcet that is able to store the items that the seller
    //selects from the database. Requires the seller/vendor to login with 
    //a id and password. The Seller class is mutable and is created for each session.
    
    //Abstraction Function: 
    //Repersents the selected items during a Sellers session. 
    //AF(c) = Seller a such that 
    //          a.items = c.items
    //          a.id = c.id
    //          a.password = c.password
    
    //Rep Invariant: 
    //      c.items!= null
    //      c.id >= 0 
    //      c.password instanceof String
    
    
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
    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) this.items.clone();
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public boolean removeItem(Item item) {
        if (item == null){ 
            return false; 
        } else { 
            items.remove(item);
            return true; 
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.items);
        return hash;
    }

    @Override
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
    
    public boolean repOk(){ 
       if(id < 0 || items == null ){ 
            return false;
        } else { 
            return true; 
        } 
    }
    // we should look this over
     @Override
    public String toString() {
        
        if (repOk() == true) { 
            return "Valid Rep Invariant"; 
        } else { 
            return "Invalid Rep Invariant"; 
        }
    }
    
    
    
    
}
