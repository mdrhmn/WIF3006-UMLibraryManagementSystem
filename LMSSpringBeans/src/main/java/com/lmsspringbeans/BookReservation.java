/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lmsspringbeans;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author muhdrahiman
 */
public class BookReservation {

    public int userID;
    public String username;
    public String fullName;
    public int bookID;
    public String bookName;

    public BookReservation() {

    }

    public BookReservation(int userID, String username, String fullName, int bookID, String bookName) {
        this.userID = userID;
        this.username = username;
        this.fullName = fullName;
        this.bookID = bookID;
        this.bookName = bookName;
    }

    public int getUserID() {
        return userID;
    }

    public BookReservation getBookReservationByUserID(int userID) {
        if (this.userID == userID) {
            return this;
        }
        return null;
    }

    public BookReservation getBookReservationByBookID(int bookID) {
        if (this.bookID == bookID) {
            return this;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getUserIDInString() {
        return String.valueOf(userID);
    }

    public String getBookIDInString() {
        return String.valueOf(bookID);
    }
}
