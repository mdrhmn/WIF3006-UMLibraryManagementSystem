
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
    public void approveReservations(Member member) {
        Reservation reservation = (new Reservation()).getInstance();

        ReservationInformation reservationInformation = reservation.reservationQueue.get(member);
        reservation.reservationQueue.remove(member);

        bookInstance.approvedReservation.put(member, reservationInformation);
        reservationInformation.changeBookStatus(true);

        System.out.println("Reservation request for " + member.name + " has been successfully approved!");
    }

    public void rejectReservations(Member member) {
        Reservation reservation = (new Reservation()).getInstance();

        ReservationInformation reservationInformation = reservation.reservationQueue.get(member);
        reservation.reservationQueue.remove(member);

        System.out.println("Reservation request for " + member.name + " has been successfully rejected!");
    }

    public void viewIssuedBooks() {
        System.out.println(bookInstance.toString());
    }

    public void memberIssuedBooks(Member member) {
        StringBuffer buffer = new StringBuffer();

        if (bookInstance.approvedReservation.containsKey(member)) {
            ReservationInformation reservationInformation = bookInstance.approvedReservation.get(member);

            System.out.println("\n#####################################################################################################");
            System.out.println("\nIssued books list for " + member.name + ":\n");

            String format = "%5s %20s %20s %10s\n";
            String headerDivider = "========================================================================";
            String rowDivider = "------------------------------------------------------------------------";

            System.out.println(headerDivider);
            System.out.println(String.format(format, "ID", "NAME", "AUTHOR", "YEAR"));
            System.out.println(headerDivider);

            for (Book book : reservationInformation.bookRequested) {
                System.out.println(String.format(format, book.id, book.name, book.author, book.year));
                System.out.println(rowDivider);
                System.out.println("\n");
            }
        } else {
            System.out.println("\n#####################################################################################################");
            System.out.println("\nNo issued books records can be found for " + member.name + ".");
        }
    }

    public void returnIssuedBooks(Member member) {
        if (bookInstance.approvedReservation.containsKey(member)) {
            ReservationInformation reservationInformation = bookInstance.approvedReservation.get(member);

            reservationInformation.changeBookStatus(false);
            bookInstance.approvedReservation.remove(member);

            System.out.println("\n#####################################################################################################");
            System.out.println("\nAll issued books for " + member.name + " have been successfully returned!");
        } else {
            System.out.println("\n#####################################################################################################");
            System.out.println("\nNo issued books records can be found for " + member.name + ".");
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        int count = 0;

        buffer.append("\n#####################################################################################################");
        buffer.append("\nIssued books list:\n");

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
