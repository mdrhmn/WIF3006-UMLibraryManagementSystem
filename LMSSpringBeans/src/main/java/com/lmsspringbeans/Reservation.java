package com.lmsspringbeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class for managing book reservations
 */
public class Reservation {

    // HashMap for storing all book reservation requests of Members
    public static HashMap<Member, ReservationInformation> reservationQueue = new HashMap<>();

    // List of BookReservations for UNAPPROVED book reservations for TableView display 
    // (BookReservation acts like a bridge class to circumvent HashMap-TableView display issue)
    public static List<BookReservation> bookReservationsList = new ArrayList<>();

    /**
     * Method to get Member's ReservationInformation
     */
    public ReservationInformation getReservation(Member member) {

        // Retrieve Member's book reservation informations from reservationQueue HashMap
        ReservationInformation reservationInformation = reservationQueue.get(member);

        // If reservationInformation is null == Member has never made a book reservation
        if (reservationInformation == null) {
            // Create new ReservationInformation record for Member
            reservationInformation = new ReservationInformation();

            // Add new Member-ReservationInformation key-value pair inside reservationQueue HashMap
            reservationQueue.put(member, reservationInformation);
        }

        return reservationInformation;
    }

    /**
     * Method to create new book reservation for Member
     *
     * ASSUMPTION: Member can only borrow 1 book 1 time only (cannot borrow
     * multiple quantities of the same book)
     */
    public Boolean makeReservation(Member member, Book requestedbook) {

        // Get Member's ReservationInformation instance using the getReservation method above
        ReservationInformation reservationInformation = getReservation(member);

        // If Member has the requested book inside their bookRequestedList list == book already reserved by Member
        if (reservationInformation.bookRequestedList.contains(requestedbook)) {
            System.out.println("Error! " + requestedbook.getName() + " is already in your reservation");

            // Return false to display error alert at GUIController
            return false;
        } else {
            // Add new requested book inside Member's bookRequestedList list
            reservationInformation.bookRequestedList.add(requestedbook);

            // Add new BookReservation record inside bookReservationsList list
            bookReservationsList.add(new BookReservation(member.getId(), member.getUsername(), member.getName(), requestedbook.getId(), requestedbook.getName()));

            System.out.println("Reservation for " + requestedbook.getName() + " has been successfully added!");

            // Return true to display success alert at GUIController
            return true;
        }
    }

    /**
     * Method for Member to cancel a book reservation
     */
    public void cancelReservation(Member member, Book requestedbook) {

        // Get Member's ReservationInformation instance using the getReservation method above
        ReservationInformation reservationInformation = getReservation(member);

        // If Member does not have the requested book inside their bookRequestedList list == do nothing
        if (!reservationInformation.bookRequestedList.contains(requestedbook)) {
            System.out.println("Error! You do not have " + requestedbook.getName() + " reserved!");
        } else {
            // Remove requested book from bookRequestedList list
            reservationInformation.bookRequestedList.remove(requestedbook);

            System.out.println("Reservation for " + requestedbook.getName() + " has been successfully cancelled!");
        }
    }

    /**
     * Method for Member to cancel all of their book reservations
     */
    public void cancelAllReservations(Member member) {

        // If Member does not have a reservation record (never borrowed a book at all before)
        if (getReservation(member) != null) {
            // If Member has a previous reservation record but didn't have a book reserved
            if (getReservation(member).bookRequestedList.isEmpty()) {
                System.out.println("Error! You do not have any books reserved!");
            } else {

                // Remove Member and their reservation information from reservationQueue HashMap
                reservationQueue.remove(member);
                System.out.println("Reservation data for " + member.getName() + " has been successfully deleted!");
            }
        } else {
            System.out.println("Error! You do not have any books reserved!");
        }
    }

    /**
     * Method to get Member's reservation status
     */
    public boolean getReservationStatus(Member member) {

        // If Member does not have a reservation record (never borrowed a book at all before)
        if (getReservation(member) != null) {
            // If Member has a previous reservation record but didn't have a book reserved
            if (getReservation(member).bookRequestedList.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to get all Members' book reservations (HashMap)
     */
    public HashMap<Member, ReservationInformation> getAllReservations() {
        return reservationQueue;
    }

    /**
     * Method to get all Members' book reservations (List)
     */
    public List<BookReservation> getAllReservationsList() {
        return bookReservationsList;
    }

    /**
     * Method to get all of a specific Members' book reservations (List)
     */
    public List<BookReservation> getAllMemberReservationsList(int userID) {
        List<BookReservation> memberReservationsList = new ArrayList<>();

        for (BookReservation br : bookReservationsList) {
            if (br.getUserID() == userID) {
                memberReservationsList.add(br);
            }
        }
        return memberReservationsList;
    }

    /**
     * Method to get a specific Members' book reservation
     */
    public BookReservation getBookReservation(int userID, int bookID) {
        for (BookReservation br : bookReservationsList) {
            if (br.getUserID() == userID && br.getBookID() == bookID) {
                return br;
            }
        }
        return null;
    }
}
