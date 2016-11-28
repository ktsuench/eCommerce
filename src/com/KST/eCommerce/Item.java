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
    //AF(c) = Item x such that
    //          x.id = c.id
    //          x.title = c.title
    //          x.description = c.description
    //          x.price = c.price
    
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

    /**
     * Class Constructor
     *
     * @param id
     * @param title
     * @param description
     * @param price
     */
    public Item(int id, String title, String description, double price) {
        //EFFECTS: initializes this to the instance variables. 

        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;

    }

    /**
     * Returns the ID of the item.
     *
     * @return int
     */
    public int getId() {
        //EFFECTS: Returns the integer id number of this. 
        return this.id;
    }

    /**
     * Returns title of the item.
     *
     * @return String
     */
    public String getTitle() {
        //EFFECTS: Returns the String title of this.
        return this.title;
    }

    /**
     * Returns the description of the item.
     *
     * @return String
     */
    public String getDescription() {
        //EFFECTS: Returns the String description of this.
        return this.description;
    }

    /**
     * Returns the price of the item.
     *
     * @return double
     */
    public double getPrice() {
        //EFFECTS: Returns the integer price of this.
        return this.price;

    }

    @Override
    public boolean equals(Object o) {
        //REQUIRES: o != null and o instance of Item
        //EFFECTS: returns true if o is the same as this, otherwise false
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
        // EFFECTS: Returns unique hash code for this.
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    /**
     * Returns a clone of this.
     *
     * @return Item
     */
    @Override
    public Item clone() {
        //EFFECTS: Returns new instance of this.
        return new Item(id, title, description, price);
    }

    public boolean repOk() {
        //EFFECTS: Returns true if the rep invariant holds for this,
        //otherwise it returns false.
        return !(id < 0 || price < 0);
    }

    @Override
    public String toString() {
        //EFFECTS: Returns the string representation of the abstraction.
        if (repOk()) {
            return "id " + getId() + " title " + getTitle() + " description " + getDescription() + " price " + getPrice();
        } else {
            return "Invalid rep invariant.";
        }
    }
}