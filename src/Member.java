
import java.util.Scanner;

public class Member extends User {

    public Member(String name, String username, String password, String role) {
        super(name, username, password, role);
    }

//    public void returnIssuedBooks() {
//
//    }
    public void viewBooks() {
        BookStorage bookStorage = (new BookStorage()).getInstance();
        bookStorage.viewBooks();
    }

    // TODO: Can reserve > 1 quantity of the same book (at the moment, it overrides)
    public void reserveBooks() {
        BookStorage bookStorage = (new BookStorage()).getInstance();
        Reservation reservation = (new Reservation()).getInstance();
        bookStorage.viewBooks();

        System.out.print("\nEnter the Book ID that you want to reserve [-1 to cancel]: ");
        Scanner scanner = new Scanner(System.in);

        //reserve book
        int bookIndex = scanner.nextInt();
        if (bookIndex != -1) {
            reservation.makeReservation(this, bookStorage.getBook(bookIndex));
        }
    }

    public void cancelAllReservations() {
        viewReservations();

        System.out.println("\nAre you sure you want to cancel all your reservations? [-1 to cancel]: ");
        System.out.println("[1] Yes");
        System.out.println("[-1] Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter action: ");
        int menuAction = scanner.nextInt();

        if (menuAction != -1) {
            Reservation reservation = (new Reservation()).getInstance();
            reservation.cancelAllReservations(this);
        }
    }

    public void cancelReservations() {
        BookStorage bookStorage = (new BookStorage()).getInstance();
        viewReservations();

        System.out.print("\nnEnter the Book ID that you want to cancel reservation: ");
        Scanner scanner = new Scanner(System.in);
        int bookIndex = scanner.nextInt();

        if (bookIndex != -1) {
            Reservation reservation = (new Reservation()).getInstance();
            reservation.cancelReservation(this, bookStorage.getBook(bookIndex));
        }
    }

    public void viewReservations() {
        Reservation reservation = (new Reservation()).getInstance();
        ReservationInformation reservationInformation = reservation.getReservation(this);

        System.out.println(reservationInformation.toString());
    }

}
