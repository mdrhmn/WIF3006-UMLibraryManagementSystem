package com.lmsspringbeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reservation {

    public static HashMap<Member, ReservationInformation> reservationQueue = new HashMap<>();
    public static List<BookReservation> bookReservationsList = new ArrayList<>();

    public ReservationInformation getReservation(Member member) {
        ReservationInformation reservationInformation = reservationQueue.get(member);
        if (reservationInformation == null) {
            reservationInformation = new ReservationInformation();
            reservationQueue.put(member, reservationInformation);
        }

        return reservationInformation;
    }

    public void makeReservation(Member member, Book book) {
        ReservationInformation reservationInformation = getReservation(member);
        if (reservationInformation.bookRequestedList.contains(book)) {
            System.out.println("Error! " + book.name + " is already in your reservation");
        } else {
            reservationInformation.bookRequestedList.add(book);
            bookReservationsList.add(new BookReservation(member.getId(), member.getUsername(), member.getName(), book.getId(), book.getName()));
            System.out.println("Reservation for " + book.name + " has been successfully added!");
        }
    }

    public void cancelReservation(Member member, Book book) {
        ReservationInformation reservationInformation = getReservation(member);
        if (!reservationInformation.bookRequestedList.contains(book)) {
        } else {
            reservationInformation.bookRequestedList.remove(book);
        }
    }

    public void cancelAllReservations(Member member) {
        if (getReservation(member) != null) {
            if (getReservation(member).bookRequestedList.isEmpty()) {
                System.out.println("Error! You do not have any books reserved!");
            } else {
                reservationQueue.remove(member);
                System.out.println("Reservation data for " + member.getName() + " has been successfully deleted!");
            }
        } else {
            System.out.println("Error! You do not have any books reserved!");
        }
    }

    public boolean getReservationStatus(Member member) {
        if (getReservation(member) != null) {
            if (getReservation(member).bookRequestedList.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public HashMap<Member, ReservationInformation> getAllReservations() {
        return reservationQueue;
    }

    public List<BookReservation> getAllReservationsList() {
        return bookReservationsList;
    }

    public List<BookReservation> getAllMemberReservationsList(int userID) {
        List<BookReservation> memberReservationsList = new ArrayList<>();

        for (BookReservation br : bookReservationsList) {
            if (br.getUserID() == userID) {
                memberReservationsList.add(br);
            }
        }
        return memberReservationsList;
    }

    public BookReservation getBookReservation(int userID, int bookID) {
        for (BookReservation br : bookReservationsList) {
            if (br.getUserID() == userID && br.getBookID() == bookID) {
                return br;
            }
        }
        return null;
    }
}
