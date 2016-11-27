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

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class Database {

    //OVERVIEW: Database is mutable. Reads file to obtain information about the
    //users and items. 
    
    //Abstraction Function: 
    //Represents the stored information for the users and items. It should also 
    //follow the xml format. 
    //AF(c) = Database x such that 
    //          x.dbFile = c.dbFile
           
    
    //Rep Invariant: 
    //      c.dbFile != null
    
    private File dbFile;

    //Constructor
    public Database(String filename) {
        dbFile = new File(filename);
    }
    
    //Methods
    public ArrayList<User> readUsers() {
        //MODIFIES: users and item
        //EFFECTS: Stores user and item information into arraylists
        ArrayList<User> users = new ArrayList<>();
        User user;
        Item item;

        try {
            XMLReader xml = new XMLReader(dbFile);

            do {
                user = new Seller(xml.getName(), xml.getId(), xml.getPassword());

                do {
                    item = new Item(xml.getItemId(), xml.getItemTitle(), xml.getItemDescription(), xml.getItemPrice());
                    ((Seller) user).addItem(item);
                } while (xml.nextItem());
                
                ArrayList<Item> items = ((Seller) user).getItems();
                String id = ""+items.get(items.size()-1).getId();
                int uid = Integer.parseInt(id.substring(1));
                ((Seller) user).setUniqueItemId(uid);
                
                users.add(user);
            } while (xml.nextUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public void writeUsers(ArrayList<User> users) {
        //EFFECTS:

    }
    
    public void closeDb() {
        //EFFECTS: Closes the dbFile. 
        this.dbFile = null;
    }
    
      public boolean repOk(){ 
        //EFFECTS: Returns ture if the rep invariant holds for this,
        //otherwise it returns false.
        return !(dbFile == null); 
    }
    
    @Override
    public String toString(){ 
        //EFFECTS:Returns the string representation of the abstraction. 
        return "Users " + readUsers(); 
    }
}
