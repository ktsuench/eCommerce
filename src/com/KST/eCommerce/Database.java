package com.KST.eCommerce;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Kent Tsuenchy
 */
public class Database {

    private File dbFile;

    public Database(String filename) {
        dbFile = new File(filename);
    }

    public ArrayList<User> readUsers() {
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

    }
    
    public void closeDb() {
        this.dbFile = null;
    }
}