package com.lmsspringbeans;

import java.util.ArrayList;

/**
 * Class for storing book reservation information
 */
public class ReservationInformation {

    // Stores the list of all issued books (approved reservations)
    public static ArrayList<Book> issuedBooksList = new ArrayList<Book>();

    // Stores the list of all books reserved/requested by Member (pending reservations)
    public static ArrayList<Book> bookRequestedList = new ArrayList<Book>();

    public ReservationInformation() {
    }

    /**
     * Method for changing book status after return
     */
    public void changeBookStatusPostReturn(boolean status, Book returnedBook) {
        if (status == true) {
            returnedBook.availableQuantity -= 1;
            returnedBook.unavailableQuantity += 1;
        } else {
            returnedBook.availableQuantity += 1;
            returnedBook.unavailableQuantity -= 1;
        }
    }

    /**
     * Method for getting Book instance using book ID
     */
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
