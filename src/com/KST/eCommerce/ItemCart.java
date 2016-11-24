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
    //Instance variables

    private ArrayList<Item> items;
    private final int numberOfItems;

    //Constructor
    public ItemCart() {
        this.numberOfItems = 0;
        ArrayList<Item> items = new ArrayList();
    }

    //Methods
    public int getSizeOfCart() {
        return this.numberOfItems;
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
    }

}
