package com.KST.eCommerce;

/**
 *
 * @author Kent Tsuenchy
 */
public class Guest extends User {

    /**
     * Class Constructor
     */
    public Guest() {
        super("Guest", User.UserRole.guest);
    }

    /**
     * Class Constructor
     *
     * @param name
     */
    public Guest(String name) {
        super(name, User.UserRole.guest);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Guest) {
            Guest g = (Guest) o;
            
            if (g.getName() != this.name) {
                return false;
            }
        } else {
            return false;
        }
        
        return true;
    }
}
