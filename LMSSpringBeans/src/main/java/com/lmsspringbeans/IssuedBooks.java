package com.lmsspringbeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class for managing all issued books/approved book reservations
 */
public class IssuedBooks {

    // List of BookReservations for APPROVED book reservations for TableView display 
    // (BookReservation acts like a bridge class to circumvent HashMap-TableView display issue)
    public static List<BookReservation> issuedBookReservationsList = new ArrayList<>();

    // HashMap for storing all approved book reservations/issued books to Members
    public static HashMap<Member, ReservationInformation> approvedReservation = new HashMap<>();

    /**
     * Method to get Member instance
     */
    public Member getMember(int memberID, Reservation reservation) {
        for (Member member : reservation.reservationQueue.keySet()) {
            if (member.getId() == memberID) {
                return member;
            }
        }
        return null;
    }

    /**
     * Method to approve Member's book reservations
     */
    public void approveReservations(Member member, Reservation reservation, int bookIndex) {

        // Retrieve Member's corresponding reservation information from reservationQueue HashMap
        ReservationInformation reservationInformation = reservation.reservationQueue.get(member);

        // Retrieve requested book's instance from ReservationInformation's bookRequestedList list using its index
        Book thisBook = reservationInformation.bookRequestedList.get(bookIndex);

        // If Member key exists in approvedReservation HashMap (Member has made other book reservations)
        if (approvedReservation.containsKey(member)) {

            // Add book to Member's record in approvedReservation HashMap
            approvedReservation.get(member).bookRequestedList.add(thisBook);

        } else {
            // Create new ReservationInformation instance
            ReservationInformation newReservationInformation = new ReservationInformation();

            // Get the book from the array and put it into the bookRequestedList list
            newReservationInformation.bookRequestedList.add(thisBook);

            // Add new Member-ReservationInformation key-value pair
            approvedReservation.put(member, newReservationInformation);
        }

        // Update the available quantity of requested book
        thisBook.availableQuantity -= 1;
        thisBook.unavailableQuantity += 1;

        // Delete the requested book record from bookRequestedList list
        reservationInformation.bookRequestedList.remove(bookIndex);

        // If Member's reservationInformation's bookRequestedList is empty == Member has 0 book reservations left
        if (reservationInformation.bookRequestedList.size() == 0) {

            // Remove Member key-value pairs from reservationQueue HashMap
            reservation.reservationQueue.remove(member);
        }

        // Add approved reservation book into issuedBooksList
        reservationInformation.issuedBooksList.add(thisBook);

        System.out.println("Reservation request for " + thisBook.getName() + " has been successfully approved!");
    }

    /**
     * Method to reject Member's book reservations
     */
    public void rejectReservations(Member member, Reservation reservation, int bookIndex) {

        // Retrieve Member's corresponding reservation information from reservationQueue HashMap
        ReservationInformation reservationInformation = reservation.reservationQueue.get(member);

        // Retrieve requested book's instance from ReservationInformation's bookRequestedList list using its index
        Book thisBook = reservationInformation.bookRequestedList.get(bookIndex);

        // Remove Member's returned book instance from their bookRequestedList list
        reservationInformation.bookRequestedList.remove(bookIndex);

        // If Member's reservationInformation's bookRequestedList is empty == Member has 0 book reservations left
        if (reservationInformation.bookRequestedList.size() == 0) {

            // Remove Member key-value pairs from reservationQueue HashMap
            reservation.reservationQueue.remove(member);
        }

        System.out.println("Reservation request for " + thisBook.getName() + " has been successfully rejected!");
    }

    /**
     * Method for Member to return borrowed books
     */
    public void returnIssuedBooks(Member member, Reservation reservation, Book returnedbook) {

        // If Member key exists in approvedReservation HashMap (Member has other books being issued to)
        if (approvedReservation.containsKey(member)) {

            // Retrieve Member's corresponding reservation information from approvedReservation HashMap
            ReservationInformation reservationInformation = approvedReservation.get(member);

            // Remove returned book from issuedBooksList
            reservationInformation.issuedBooksList.remove(returnedbook);

            // Change returned book status
            reservationInformation.changeBookStatusPostReturn(false, returnedbook);

            // Remove member from approvedReservation only if member has no more books borrowed/issued to
            if (reservationInformation.issuedBooksList.size() == 0) {
                approvedReservation.remove(member);
            }

            System.out.println(returnedbook.getName() + " has been successfully returned!");
        } else {
            System.out.println("No issued books records can be found.");
        }

    }

    /**
     * Method to get all Members' issuedBooks (HashMap)
     */
    public HashMap<Member, ReservationInformation> getAllIssuedBooks() {
        return approvedReservation;
    }

    /**
     * Method to get a specific Member's issuedBooks (List)
     */
    public List<Book> getAllMemberIssuedBooksList(User user) {
        List<Book> memberIssuedBooksList = new ArrayList<>();

        for (BookReservation br : issuedBookReservationsList) {
            if (br != null) {
                if (br.getUserID() == user.getId()) {
                    ReservationInformation reservationInformation = approvedReservation.get(user);

                    if (reservationInformation != null) {
                        return reservationInformation.issuedBooksList;
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method to get a Member's specific book's BookReservation instance
     */
    public BookReservation getIssuedBookReservation(int userID, int bookID) {
        for (BookReservation br : issuedBookReservationsList) {
            if (br.getUserID() == userID && br.getBookID() == bookID) {
                return br;
            }
        }
        return null;
    }
}
