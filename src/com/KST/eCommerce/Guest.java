package com.KST.eCommerce;

/**
 *
 * @author Kent Tsuenchy
 */
public class Guest extends User {
    /**
     * Class Constructor
     *
     * @param name
     */
    public Guest(String name) {
        super(name, User.UserRole.guest);
    }
}
