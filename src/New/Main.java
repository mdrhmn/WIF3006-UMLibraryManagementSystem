package New;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BookStorage bookStorage = new BookStorage();
        Books book = null;
        ArrayList<Books> bookList = bookStorage.viewBook();

        // init
        bookStorage.registerBook(new Books("The Bad Beginning", "Lemony Snicket", 2003, 5));
        bookStorage.registerBook(new Books("The Lightning Thief", "Rick Riordan", 2012, 5));

        Scanner scanner = new Scanner(System.in);
        int choice;
        String bookTitle;
        String bookAuthor;
        int bookYear;
        int bookQuantity;

        while (true) {
            System.out.println("\n1. Register Book");
            System.out.println("2. View Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.println("\nChoose an option:");

            choice = scanner.nextInt();
            scanner.nextLine();
            int bookIndex;

            if (choice > 0 && choice < 5) {

                switch (choice) {
                    case 1:
                        System.out.println("\nEnter book title: ");
                        bookTitle = scanner.nextLine();

                        System.out.println("\nEnter book author: ");
                        bookAuthor = scanner.nextLine();

                        System.out.println("\nEnter book's publish year: ");
                        bookYear = scanner.nextInt();
                        
                        System.out.println("\nEnter book's quantity: ");
                        bookQuantity = scanner.nextInt();

                        bookStorage.registerBook(new Books(bookTitle, bookAuthor, bookYear, bookQuantity));
                        break;

                    case 2:
                        bookIndex = 0;
                        for (Books _book : bookList) {
                            bookIndex++;
                            System.out.printf("\n%1$d. %2$s\t\t%3$s\t\t%4$d\n", bookIndex, _book.getTitle(), _book.getAuthor(), _book.getYear());
                        }
                        break;

                    case 3:
                        bookIndex = 0;
                        for (Books _book : bookList) {
                            bookIndex++;
                            System.out.printf("\n%1$d. %2$s\t\t%3$s\t\t%4$d\n", bookIndex, _book.getTitle(), _book.getAuthor(), _book.getYear());
                        }
                        
                        System.out.println("\nChoose Book ID to update");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.println("\nEnter new title: ");
                        bookTitle = scanner.nextLine();

                        System.out.println("\nEnter new author: ");
                        bookAuthor = scanner.nextLine();

                        System.out.println("\nEnter new publish year: ");
                        bookYear = scanner.nextInt();
                        
                        bookIndex = 0;
                        for (Books _book : bookList) {
                            bookIndex++;
                            if(bookIndex == choice){
                                _book.setAuthor(bookAuthor);
                                _book.setTitle(bookTitle);
                                _book.setYear(bookYear);
                            }
                        }
                        
                        break;
                        
                    case 4:
                        bookIndex = 0;
                        for (Books _book : bookList) {
                            bookIndex++;
                            System.out.printf("%1$d. %2$s\t\t%3$s\t\t%4$d\n", bookIndex, _book.getTitle(), _book.getAuthor(), _book.getYear());
                        }

                        System.out.println("\nChoose Book ID to delete");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        bookStorage.deleteBook(choice);
                        
                        System.out.println("\nUpdated book:\n");
                        bookIndex = 0;
                        for (Books _book : bookList) {
                            bookIndex++;
                            System.out.printf("%1$d. %2$s\t\t%3$s\t\t%4$d\n", bookIndex, _book.getTitle(), _book.getAuthor(), _book.getYear());
                        }
                        break;

                    default:
                        break;
                }

            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice. Try again");
            }

        }

    }

}
