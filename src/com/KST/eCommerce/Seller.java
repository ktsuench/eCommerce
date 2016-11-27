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
    //Represents the selected items during a Sellers session. 
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
    private int itemId = -1;

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
        //EFFECTS: returns id property of this.
        return this.id;
    }

    /**
     * Validates that password is correct.
     *
     * @param password
     * @return boolean
     */
    public boolean validPassword(String password) {
        //REQUIRES: password != null
        //EFFECTS: Returns true if the password equals this password.
        if (password == null) {
            return false;
        } else {
            return this.password.equals(password);
        }
    }

    /**
     * Sets the id for which new items will begin incrementing from.
     * 
     * @param i 
     */
    public void setUniqueItemId(int i) {
        //REQUIRES: i >= 0 and itemId == -1
        //MODIFIES: itemId
        //EFFECTS: sets the value of itemId
        if(itemId == -1 && i >= 0) {
            this.itemId = i;
        }
    }
    
    /**
     * Retrieves the next unique item id.
     * 
     * @return int 
     */
    public int createUniqueItemId() {
        //EFFECTS: returns the next unique item id
        return Integer.parseInt(""+this.id+(++this.itemId));
    }
    
    /**
     * Retrieves a deep copy of the sellers items.
     * 
     * @return 
     */
    @Override
    public ArrayList<Item> getItems() {
         //EFFECTS: Returns clone of items for this.
        return (ArrayList<Item>) this.items.clone();
    }

    /**
     * Adds item to list of items.
     * 
     * @param item 
     */
    @Override
    public void addItem(Item item) {
        //REQUIRES: item != null
        //MODIFIES: items
        //EFFECTS: Overrides the abstract method addItem. Adds the selected item
        //to the arraylist. 
        items.add(item);
    }

    /**
     * Removes item from list of items.
     * 
     * @param item
     * @return 
     */
    @Override
    public boolean removeItem(Item item) {
        //MODIFIES: items
        //EFFECTS: Overrides the abstract method removeItem. removes the selected 
        //item from the arraylist.
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
        //EFFECTS: Returns ture if the rep invariant holds for this,
        //otherwise it returns false.
        return !(id < 0 || items == null); 
    }
   
    @Override
    public String toString() {
       //EFFECTS: Returns the string representation of the abstraction. 
       return "id " + getId() + " items " + getItems(); 
    }
    
    
    
    
}
