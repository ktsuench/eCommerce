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
import java.util.Objects;

/**
 *
 * @author Tejveer Jajj
 */


public class ItemCart implements ItemList {
    
    //OVERVIEW: Creates an object that stores the items that the user selects. 
    //The ItemCart is mutable and is created for each session.  
    
    //Abstraction Function: 
    //Represents the selected items during a users session.
    //AF(c) = ItemCart x such that
    //          x.items = c.items
    //          x.numberOfItems = c.numberOfItems
    
    //Rep Invariant: 
    //      c.items != null && c.items is instanceof item. 
    //      c. numberOfItems >= 0
 
    
    //Instance variables
    private final ArrayList<Item> items;
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
     * @return ArrayList&lt;Item&gt;
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
    
    public boolean containsItem(Item item) {
        //REQUIES: item != null
        //EFFECTS: returns the result of arraylist containing item
        
        if (item != null) {
            return items.contains(item);
        }    
        return false;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof ItemCart) {
            ItemCart i = (ItemCart) o;
            
            if (i.getSizeOfCart() != this.numberOfItems) {
                return false;
            } else {
                Item a;
                Item b;
                
                for (int j = 0; j < numberOfItems; j++) {
                    a = i.getItems().get(j);
                    b = items.get(j);
                    
                    if(!a.equals(b)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.items);
        hash = 29 * hash + this.numberOfItems;
        return hash;
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
      return "Number of Items " + getSizeOfCart()+ " items " + getItems();  
      
    }
     
  
}
