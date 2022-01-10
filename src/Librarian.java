
import java.util.Scanner;

public class Librarian extends User {

    public Librarian(String name, String username, String password, String role) {
        super(name, username, password, role);
    }

    public void registerBook(String bookName, String bookAuthor, int bookYear, int bookQuantity) {
        BookStorage bookStorage = (new BookStorage()).getInstance();
        Book newBook = new Book(bookName, bookAuthor, bookYear, bookQuantity, 0);
        bookStorage.registerBook(newBook);
    }

    public void updateBook(int choice, String bookAuthor, String bookTitle, int bookYear, int bookQuantity) {
        BookStorage bookStorage = (new BookStorage()).getInstance();
        bookStorage.updateBook(choice, bookAuthor, bookTitle, bookYear, bookQuantity);
    }

    public void deleteBook(int bookIndex) {
        BookStorage bookStorage = (new BookStorage()).getInstance();
        bookStorage.deleteBook(bookIndex);
    }

    public void viewBooks() {
        BookStorage bookStorage = (new BookStorage()).getInstance();
        bookStorage.viewBooks();
    }

    public void issueBooks() {
        IssuedBooks issuedBooks = (new IssuedBooks()).getInstance();
        issuedBooks.viewReservationQueue();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSelect an action: ");
        System.out.println("[1] Approve Reservation");
        System.out.println("[2] Reject Reservation");
        System.out.println("[-1] Exit");

        System.out.print("\nEnter action: ");
        int menuAction = scanner.nextInt();
        scanner.nextLine();

        switch (menuAction) {
            case 1: {
                System.out.print("Enter the Reservation ID that you want to approve reservation [-1 to cancel]: ");
                int memberIndex = scanner.nextInt();

                if (memberIndex != -1) {
                    issuedBooks.approveReservations(issuedBooks.getMember(memberIndex));
                }

                break;
            }
            case 2: {
                System.out.print("Enter the Reservation ID that you want to reject reservation [-1 to cancel]: ");
                int memberIndex = scanner.nextInt();

                if (memberIndex != -1) {
                    issuedBooks.rejectReservations(issuedBooks.getMember(memberIndex));
                }
                break;
            }
            default:
                throw new IllegalArgumentException("\nUnexpected value: " + menuAction);

        }
    }

    public void viewIssuedBooks() {
        IssuedBooks issuedBooks = (new IssuedBooks()).getInstance();
        System.out.println(issuedBooks);
    }

}
