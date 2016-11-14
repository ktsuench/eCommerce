package com.KST.eCommerce;

/**
 *
 * @author Kent Tsuenchy
 */
public abstract class User {

    /**
     * OVERVIEW
     */
    // Enumeration for User Roles
    public enum UserRole {
        seller, guest
    }

    // Instance Variables
    protected String name;
    protected UserRole role;

    /**
     * Class Constructor
     *
     * @param name
     */
    public User(String name) {
        this.name = name;
        this.role = User.UserRole.guest;
    }

    /**
     * Class Constructor
     *
     * @param name
     * @param role
     */
    public User(String name, UserRole role) {
        this.name = name;
        this.role = role;
    }

    /**
     * Returns name of the user.
     *
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns role of the user.
     *
     * @return this.role
     */
    public UserRole getRole() {
        return this.role;
    }
}
