
import java.util.ArrayList;
import java.util.Formatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookStorage {

    private static BookStorage bookStorageInstance;
    ArrayList<Book> books;

    public BookStorage() {
    }

    public static BookStorage getInstance() {
        if (bookStorageInstance == null) {
            bookStorageInstance = new BookStorage();

            // Pre-defined set of books
            bookStorageInstance.books = new ArrayList<Book>();
            for (int i = 1; i <= 5; i++) {
                bookStorageInstance.books.add(new Book("Book " + i, "Author " + i, 2000 + i, 3, 0));
            }
        }
        return bookStorageInstance;
    }

    public void viewBooks() {
        System.out.println("\n#####################################################################################################");

        if (books.size() == 0) {
            System.out.println("\nThere are no books registered in the system.");
        } else {
            System.out.println("\nList of available books:\n");

            String format = "%5s %20s %20s %10s %10s\n";
            String headerDivider = "========================================================================";
            String rowDivider = "------------------------------------------------------------------------";

            System.out.println(headerDivider);
            System.out.printf(format, "ID", "NAME", "AUTHOR", "YEAR", "QUANTITY");
            System.out.println(headerDivider);

            for (Book book : books) {
                System.out.format(format, book.id, book.name, book.author, book.year, book.availableQuantity);
                System.out.format(rowDivider);
                System.out.println();
            }
        }
    }

    public void registerBook(Book newBook) {
        books.add(newBook);
    }

    public void updateBook(int choice, String bookAuthor, String bookTitle, int bookYear, int bookQuantity) {
        int bookIndex = 0;
        for (Book _book : books) {
            if (bookIndex == choice) {
                _book.setAuthor(bookAuthor);
                _book.setTitle(bookTitle);
                _book.setYear(bookYear);
                _book.setAvailableQuantity(bookQuantity);
            }
            bookIndex++;
        }

    }

    public void deleteBook(int bookIndex) {
        books.remove(bookIndex - 1);
    }

    public Book getBook(int index) {
        return books.get(index);
    }

}
