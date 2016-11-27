/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KST.eCommerce;

import java.util.ArrayList;

/**
 *
 * @author Tejveer Jajj
 */


public class ItemCart implements ItemList {
    
    //OVERVIEW: Creates an object that stores the items that the user selects. 
    //The ItemCart is mutable and is created for each session.  
    
    //Abstraction Function: 
    //Represents the selected items during a users session.
    //AF(c) = ItemCart a such that
    //          a.items = c.items
    //          a.numberOfItems = c.numberOfItems
    
    //Rep Invariant: 
    //      c.items != null && c.items is instanceof item. 
    //      c. numberOfItems >= 0
 
    
    //Instance variables
    private ArrayList<Item> items;
    private int numberOfItems;

    //Constructor
    public ItemCart() {
        //EFFECTS: initinalizes the number of items to 0,
        //         also initinalizes items as a new ArrayList.  
        this.numberOfItems = 0;
        this.items = new ArrayList();
    }

    //Methods
    public int getSizeOfCart() {
        //EFFECTS: Returns the integer number of this. 
        return this.numberOfItems;
    }

    /**
     * Retrieves a copy of the list of items
     * 
     * @return ArrayList<Item>
     */
    @Override
    public ArrayList<Item> getItems() {
        //EFFECTS: Returns clone of items for this.
        return (ArrayList<Item>) this.items.clone();
    }
    
    @Override
    public void addItem(Item item) {
        //REQUIRES: item != null
        //MODIFIES: items, numberOfItems
        //EFFECTS: overrides the abstract method addItem. Adds the selected item 
        //to the arraylist and increments numberOfItems by 1.
        
        if(item != null) {
            items.add(item);
            numberOfItems++;
        }
    }

    @Override
    public boolean removeItem(Item item) {
        //REQUIES: item != null
        //MODIFIES: items, numberOfItems
        //EFFECTS: overrides the abstract method removeItem. removes the selected 
        //item from the arraylist and decrements numberOfItems by 1.
       if(item == null){ 
           return false; 
       }else{ 
          items.remove(item);
          numberOfItems--;
          return true;
       }
         
    }
    
    /**
     * Clones a new instance of current instance.
     * @return ItemCart
     */
    @Override
    public ItemCart clone() {
        //EFFECTS: returns a deep copy of current instance.
        ItemCart cart = new ItemCart();
        
        for(Item i: items) {
            cart.addItem((Item) i.clone());
        }
        
        return cart;
    }
    
    public boolean repOk(){ 
        //EFFECTS: Returns true if the rep invariant holds for this,
        //otherwise it returns false.
        return !(items == null || numberOfItems < 0);
    }
    
    @Override
    public String toString() {
      //EFFECTS: Returns the string representation of the abstraction. 
      return "Number of Items " + getSizeOfCart()+ "items " + getItems();  
      
    }
     
  
}
