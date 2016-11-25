/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KST.eCommerce;

import java.util.ArrayList;

/**
 *
 * @author Tejveer
 */


public class ItemCart implements ItemList {
    
    //OVERVIEW: Creates an object that stores the items that the user selects. 
    //The ItemCart is mutable and is created for each session.  
    
    //Abstraction Function: 
    //Repersents the selected items during a users session.
    //AF(c): c.items = the items as selected by the user. Of type item.
    //       c.numberOfItems = integer for the number of selected items.
    
    //Rep Invariant: 
    //c.items != null
    //c. numberOfItems >= 0
 
    
    //Instance variables
    private ArrayList<Item> items;
    private final int numberOfItems;

    //Constructor
    public ItemCart() {
        //EFFECTS: initinalizes the number of items to 0,
        //         also initinalizes items as a new ArrayList.  
        
        this.numberOfItems = 0;
        ArrayList<Item> items = new ArrayList();
    }

    //Methods
    public int getSizeOfCart() {
        //EFFECTS: Returns the integer number of this. 
        return this.numberOfItems;
    }

    @Override
    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) this.items.clone();
    }
    
    @Override
    public void addItem(Item item) {
        //MODIFIES: items
        //EFFECTS: overrides the abstract method addItem. Adds the selected item 
        //to the arraylist. 
        items.add(item);
    }

    @Override
    public void removeItem(Item item) {
        //MODIFIES: items
        //EFFECTS: overrides the abstract method removeItem. removes the selected 
        //item from the arraylist. 
        items.remove(item);
    }
    
    public boolean repOk(){ 
        //EFFECTS: Returns ture if the rep invariant holds for this, 
        //otherwise it returns false.   
        if(items == null || numberOfItems < 0){ 
            return false; 
        }else{ 
        return true ;
        }
    }
    
    @Override
    public String toString() {
        
        if (repOk() == true) { 
            return "Valid Rep Invariant"; 
        } else { 
            return "Invalid Rep Invariant"; 
        }
    }
     
  
}
