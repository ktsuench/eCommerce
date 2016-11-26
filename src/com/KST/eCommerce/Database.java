package com.KST.eCommerce;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class Database {

    //OVERVIEW: Database is mutable. Reads file to obtain information about the
    //users and items. 
    
    private File dbFile;

    //Constructor
    public Database(String filename) {
        dbFile = new File(filename);
    }
    
    //Methods
    public ArrayList<User> readUsers() {
        //MODIFIES:
        //EFFECTS: 
        ArrayList<User> users = new ArrayList<>();
        User user;
        Item item;

        try {
            XMLHandler xml = new XMLHandler(dbFile);

            do {
                user = new Seller(xml.getName(), xml.getId(), xml.getPassword());

                do {
                    item = new Item(xml.getItemId(), xml.getItemTitle(), xml.getItemDescription(), xml.getItemPrice());
                    ((Seller) user).addItem(item);
                } while (xml.nextItem());

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
        //EFFECTS:
        this.dbFile = null;
    }
}
