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

/**
 *
 * @author Kent Tsuenchy
 */
public abstract class User {

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
