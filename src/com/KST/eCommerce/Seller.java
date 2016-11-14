package com.KST.eCommerce;

/**
 *
 * @author Kent Tsuenchy
 */
public class Seller extends User {

    // Instance Variables
    private final String password;

    /**
     * Class Constructor
     *
     * @param name
     * @param password
     */
    public Seller(String name, String password) {
        super(name, User.UserRole.seller);
        this.password = password;
    }

    /**
     * Validates that password is correct.
     *
     * @param password
     * @return boolean
     */
    public boolean validPassword(String password) {
        return this.password.equals(password);
    }
}
