
import java.util.Scanner;

public class Member extends User {

    public Member(String name, String username, String password, String role) {
        super(name, username, password, role);
    }

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

        if (getReservationStatus() == true) {
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
        } else {
            System.out.println("\n#####################################################################################################");
            System.out.println("\nNo reservation records can be found.");
        }
    }

    public void cancelReservations() {
        BookStorage bookStorage = (new BookStorage()).getInstance();

        if (getReservationStatus() == true) {
            viewReservations();

            System.out.print("\nEnter the Book ID that you want to cancel reservation: ");
            Scanner scanner = new Scanner(System.in);
            int bookIndex = scanner.nextInt();

            if (bookIndex != -1) {
                Reservation reservation = (new Reservation()).getInstance();
                reservation.cancelReservation(this, bookStorage.getBook(bookIndex));
            }
        } else {
            System.out.println("\n#####################################################################################################");
            System.out.println("\nNo reservation records can be found.");
        }

    }

    public void viewReservations() {
        Reservation reservation = (new Reservation()).getInstance();
        ReservationInformation reservationInformation = reservation.getReservation(this);

        System.out.println(reservationInformation.toString());
    }

    public boolean getReservationStatus() {
        Reservation reservation = (new Reservation()).getInstance();
        return reservation.getReservationStatus(this);
    }

    public void viewIssuedBooks() {
        IssuedBooks issuedBooks = (new IssuedBooks()).getInstance();
        issuedBooks.memberIssuedBooks(this);
    }

    public void returnIssuedBooks() {
        IssuedBooks issuedBooks = (new IssuedBooks()).getInstance();
        issuedBooks.returnIssuedBooks(this);
    }
}
