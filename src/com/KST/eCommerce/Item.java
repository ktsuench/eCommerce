/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.KST.eCommerce;

/**
 *
 * @author Tejveer
 */
public class Item {
    
    //OVERVIEW: Creates an object for each of the items in the database. Each  
    //Item objects is immuatable, with each different item having fixed 
    //parameters for the title, id ect. 
    
    //Abstraction Function: 
    //Represents individual the items for sale, where each item must have certian 
    //information.
    //AF(c) = Item a such that
    //          a.id = c.id
    //          a.title = c.title
    //          a.description = c.description
    //          a.price = c.price     
    
    //Rep Invariant: 
    //      c.title instanceof String
    //      c.description instanceof String 
    //      c.id >= 0
    //      c.price >= 0.00
    
    //Instance Variables
    private final int id;
    private final String title;
    private final String description;
    private final double price;

    //Constructor
    public Item(int id, String title, String description, double price) {
        //EFFECTS: initializes this to the instance variables. 
        
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;

    }

    //Methods
    public int getId() {
        //EFFECTS: Returns the integer id number of this. 
        return this.id;
    }

    public String getTitle() {
        //EFFECTS: Returns the String title of this.
        return this.title;
    }

    public String getDescription() {
        //EFFECTS: Returns the String description of this.
        return this.description;
    }

    public double getPrice() {
        //EFFECTS: Returns the integer price of this.
        return this.price;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof Item) {
            Item t = (Item) o;

            if (t.id != this.id) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }
    
    @Override
    public Item clone() {
        return new Item(id, title, description, price);
    }
    
    public boolean repOk(){ 
        //EFFECTS: Returns true if the rep invariant holds for this, 
        //otherwise it returns false.    
        if(id < 0 || price < 0 ){ 
            return false;
        } else { 
            return true; 
        }  
        
        
    } 
    
    @Override
    public String toString() {
        //EFFECTS: Returns the string representation of the abstraction. 
        return "id " + getId() + " title "+ getTitle() + " description "+ getDescription() + " price "+ getPrice(); 
    }
}
