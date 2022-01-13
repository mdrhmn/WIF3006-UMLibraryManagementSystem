package com.lmsspringbeans;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IssuedBooks {

    public static List<BookReservation> issuedBookReservationsList = new ArrayList<>();
    public static HashMap<Member, ReservationInformation> approvedReservation = new HashMap<>();

    public Member getMember(int memberID, Reservation reservation) {
        for (Member member : reservation.reservationQueue.keySet()) {
            if (member.getId() == memberID) {
                return member;
            }
        }
        return null;
    }

    public void approveReservations(Member member, Reservation reservation, int bookIndex) {
        ReservationInformation reservationInformation = reservation.reservationQueue.get(member);
        Book thisBook = reservationInformation.bookRequestedList.get(bookIndex);

        if (approvedReservation.containsKey(member)) {
            // Set the data into the already created one
            approvedReservation.get(member).bookRequestedList.add(thisBook);

        } else {
            ReservationInformation newReservationInformation = new ReservationInformation();
            // Get the book from the array and put it into the request
            newReservationInformation.bookRequestedList.add(thisBook);
            approvedReservation.put(member, newReservationInformation);
        }

        // Update the available quantity
        thisBook.availableQuantity -= 1;
        thisBook.unavailableQuantity += 1;

        // Delete the request
        reservationInformation.bookRequestedList.remove(bookIndex);
        if (reservationInformation.bookRequestedList.size() == 0) {
            reservation.reservationQueue.remove(member);
        }

        // Add approved reservation book
        reservationInformation.issuedBooksList.add(thisBook);

        System.out.println("Reservation request for " + thisBook.getName() + " has been successfully approved!");
    }

    public void rejectReservations(Member member, Reservation reservation, int bookIndex) {
        ReservationInformation reservationInformation = reservation.reservationQueue.get(member);

        // Delete the request
        Book thisBook = reservationInformation.bookRequestedList.get(bookIndex);

        reservationInformation.bookRequestedList.remove(bookIndex);
        if (reservationInformation.bookRequestedList.size() == 0) {
            reservation.reservationQueue.remove(member);
        }

        System.out.println("Reservation request for " + thisBook.getName() + " has been successfully rejected!");
    }

    public void returnIssuedBooks(Member member, Reservation reservation, Book returnedbook) {

        if (approvedReservation.containsKey(member)) {
            ReservationInformation reservationInformation = approvedReservation.get(member);

            // Remove returned book from issuedBooksList
            reservationInformation.issuedBooksList.remove(returnedbook);

            // Change returned book status
            reservationInformation.changeBookStatusPostReturn(false, returnedbook);

            // Remove member from approvedReservation only if member has no more books borrowed
            if (reservationInformation.issuedBooksList.size() == 0) {
                approvedReservation.remove(member);
            }

            System.out.println(returnedbook.getName() + " has been successfully returned!");
        } else {
            System.out.println("No issued books records can be found.");
        }

    }

    public HashMap<Member, ReservationInformation> getAllIssuedBooks() {
        return approvedReservation;
    }

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

    public BookReservation getIssuedBookReservation(int userID, int bookID) {
        for (BookReservation br : issuedBookReservationsList) {
            if (br.getUserID() == userID && br.getBookID() == bookID) {
                return br;
            }
        }
        return null;
    }
}
