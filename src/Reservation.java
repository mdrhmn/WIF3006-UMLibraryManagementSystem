
import java.util.HashMap;

public class Reservation {

    private static Reservation reservationInstance;
    HashMap<Member, ReservationInformation> reservationQueue;

    public static Reservation getInstance() {
        if (reservationInstance == null) {
            reservationInstance = new Reservation();
            reservationInstance.reservationQueue = new HashMap<Member, ReservationInformation>();
        }
        return reservationInstance;
    }

    // 
    public ReservationInformation getReservation(Member member) {
        ReservationInformation reservationInformation = reservationInstance.reservationQueue.get(member);
        if (reservationInformation == null) {
            reservationInformation = new ReservationInformation();
            reservationInstance.reservationQueue.put(member, reservationInformation);
        }
        return reservationInformation;
    }

    public void makeReservation(Member member, Book book) {
        ReservationInformation reservationInformation = reservationInstance.getReservation(member);
        if (reservationInformation.bookRequested.contains(book)) {
            System.out.println("Error! " + book.name + " is already in your reservation");
        } else {
            reservationInformation.bookRequested.add(book);
            System.out.println("Reservation for " + book.name + " has been successfully added!");
        }
        System.out.println(reservationInformation);
    }

    public void cancelReservation(Member member, Book book) {
        ReservationInformation reservationInformation = reservationInstance.getReservation(member);
        if (!reservationInformation.bookRequested.contains(book)) {
            System.out.println("Error! " + book.name + " is not in your reservation");
        } else {
            reservationInformation.bookRequested.remove(book);
            System.out.println("Reservation for " + book.name + " has been successfully deleted!");
        }
        System.out.println(reservationInformation);
    }

    public void cancelAllReservations(Member member) {
        if (reservationInstance.getReservation(member) != null) {
            if (reservationInstance.getReservation(member).bookRequested.isEmpty()) {
                System.out.println("Error! You do not have any books reserved!");
            } else {
                reservationInstance.reservationQueue.remove(member);
                System.out.println("Reservation data for " + member.name + " has been successfully deleted!");
            }

        } else {
            System.out.println("Error! You do not have any books reserved!");
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        int count = 0;

        buffer.append("\n#####################################################################################################");

        buffer.append("\n\nReservation list:\n");

        String format = "%5s %20s %20s %10s\n";
        String headerDivider = "========================================================================";
        String rowDivider = "------------------------------------------------------------------------";

        ReservationInformation ri;

        if (reservationInstance.reservationQueue.keySet().isEmpty()) {
            buffer.append("No reservation records can be found.");
        } else {
            for (Member member : reservationInstance.reservationQueue.keySet()) {
                buffer.append("ID\tMEMBER NAME\n");
                buffer.append(count + "\t" + member.name + "\n");

                buffer.append("\n\tReservation list for " + member.name + ":\n");
                buffer.append("\t" + headerDivider + "\n");
                buffer.append("\t" + String.format(format, "ID", "NAME", "AUTHOR", "YEAR"));
                buffer.append("\t" + headerDivider + "\n");

                ri = reservationInstance.reservationQueue.get(member);

                for (Book book : ri.bookRequested) {
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
