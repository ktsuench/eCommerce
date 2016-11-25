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
public interface ItemList {

    //Abstract methods
    public ArrayList<Item> getItems();
    
    public void addItem(Item item);

    public boolean removeItem(Item item);
}
