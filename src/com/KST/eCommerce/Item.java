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

    //Instance Variables
    private final int id;
    private final String title;
    private final String description;
    private final double price;

    //Constructor
    public Item(int id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;

    }

    //Methods
    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;

    }

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
}
