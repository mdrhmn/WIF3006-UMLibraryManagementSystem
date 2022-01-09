
import java.util.HashMap;

public class IssuedBooks {

    private static IssuedBooks bookInstance;
    HashMap<Member, ReservationInformation> approvedReservation;

    public static IssuedBooks getInstance() {
        if (bookInstance == null) {
            bookInstance = new IssuedBooks();
            bookInstance.approvedReservation = new HashMap<>();
        }
        return bookInstance;
    }

    public Member getMember(int memberIndex) {
        Reservation reservation = (new Reservation()).getInstance();
        int count = 0;

        for (Member member : reservation.reservationQueue.keySet()) {
            if (count == memberIndex) {
                return member;
            } else {
                count++;
            }
        }
        return null;
    }

    public void viewReservationQueue() {
        Reservation reservation = (new Reservation()).getInstance();
        System.out.println(reservation);
    }

    // TODO: Add/reject book reservations at book level instead of user level
    public void addIssuedBooks(Member member) {
        Reservation reservation = (new Reservation()).getInstance();

        ReservationInformation reservationInformation = reservation.reservationQueue.get(member);
        reservation.reservationQueue.remove(member);

        bookInstance.approvedReservation.put(member, reservationInformation);
        reservationInformation.changeBookStatus(true);

        System.out.println("Reservation request for " + member.name + " has been successfully approved!");
    }

    public void removeIssuedBooks(Member member) {
        Reservation reservation = (new Reservation()).getInstance();

        ReservationInformation reservationInformation = reservation.reservationQueue.get(member);
        reservation.reservationQueue.remove(member);

        System.out.println("Reservation request for " + member.name + " has been successfully rejected!");
    }

    public void viewIssuedBooks() {
        System.out.println(bookInstance.toString());
    }

    public void updateIssuedBooks() {
    }

    public void returnIssuedBooks() {
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        int count = 0;

        buffer.append("\n#####################################################################################################");
        buffer.append("\nIssued book reservation list:\n");

        String format = "%5s %20s %20s %10s\n";
        String headerDivider = "========================================================================";
        String rowDivider = "------------------------------------------------------------------------";

        ReservationInformation reservationInformation;

        if (bookInstance.approvedReservation.keySet().isEmpty()) {
            buffer.append("No issued books records can be found.");
        } else {

            for (Member member : bookInstance.approvedReservation.keySet()) {
                buffer.append("ID\tMEMBER NAME\n");
                buffer.append(count + "\t" + member.name + "\n");

                buffer.append("\n\tReservation list for " + member.name + ":\n");
                buffer.append("\t" + headerDivider + "\n");
                buffer.append("\t" + String.format(format, "ID", "NAME", "AUTHOR", "YEAR"));
                buffer.append("\t" + headerDivider + "\n");

                reservationInformation = bookInstance.approvedReservation.get(member);

                for (Book book : reservationInformation.bookRequested) {
                    buffer.append("\t" + String.format(format, book.id, book.name, book.author, book.year));
                    buffer.append("\t" + rowDivider);
                    buffer.append("\n");
                }
                count++;
            }
        }
        return buffer.toString();
    }
}
