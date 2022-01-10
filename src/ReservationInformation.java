
import java.util.ArrayList;

public class ReservationInformation {

    ArrayList<Book> bookRequested;

    public ReservationInformation() {
        bookRequested = new ArrayList<Book>();
    }

    public void changeBookStatus(boolean status) {
        if (status == true) { // book is issued
            for (Book book : bookRequested) {
                book.availableQuantity -= 1;
                book.unavailableQuantity += 1;
            }
        } else { // book is returned
            for (Book book : bookRequested) {
                book.availableQuantity += 1;
                book.unavailableQuantity -= 1;
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n#####################################################################################################");

        if (bookRequested.isEmpty()) {
            buffer.append("\n\nNo reservation records can be found.");
        } else {
            buffer.append("\n\nList of your reserved books:\n");
            String format = "%5s %20s %20s %10s %10s\n";
            String headerDivider = "========================================================================";
            String rowDivider = "------------------------------------------------------------------------";

            buffer.append(headerDivider + "\n");
            buffer.append(String.format(format, "ID", "NAME", "AUTHOR", "YEAR", "QUANTITY"));
            buffer.append(headerDivider + "\n");

            for (Book book : bookRequested) {
                buffer.append(String.format(format, book.id, book.name, book.author, book.year, book.availableQuantity));
                buffer.append(rowDivider);
                buffer.append("\n");
            }

        }

        return buffer.toString();

    }

}
