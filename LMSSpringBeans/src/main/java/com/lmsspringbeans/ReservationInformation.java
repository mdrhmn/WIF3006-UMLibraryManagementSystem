package com.lmsspringbeans;

import java.util.ArrayList;

public class ReservationInformation {

    // Stores the list of all issued books
    public static ArrayList<Book> issuedBooksList = new ArrayList<Book>();
    // Stores the list of all books reserved/requested by Member
    public static ArrayList<Book> bookRequestedList = new ArrayList<Book>();

    public ReservationInformation() {
    }

    public void changeBookStatusPostReturn(boolean status, Book returnedBook) {
        if (status == true) {
            returnedBook.availableQuantity -= 1;
            returnedBook.unavailableQuantity += 1;
        } else {
            returnedBook.availableQuantity += 1;
            returnedBook.unavailableQuantity -= 1;
        }
    }

    public int getBookIndexById(int bookID) {
        int count = 0;

        for (Book book : bookRequestedList) {
            if (book.id == bookID) {
                return count;
            }
            count++;
        }
        return 0;
    }

}
