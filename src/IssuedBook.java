
import java.util.HashMap;
import java.util.Map;

public class IssuedBook {

    private Map<Books, String> approvedReservation = new HashMap<Books, String>();
//    private Map<Member, ReservationInformation> approvedReservation = new HashMap<Member, ReservationInformation>();

    public void issueBook(Books book, String name) {
        if (approvedReservation.containsKey(book)) {
            name += approvedReservation.get(book);
        }
        approvedReservation.put(book, name);
    }

    public Map<Books, String> viewIssuedBook() {
        return approvedReservation;
    }

    public void updateIssuedBook(Books book) {
        if (!approvedReservation.containsKey(book)) {
            return;
        } else {
//            book.setAuthor(author);
        }

    }

    public void returnIssuedBook(Books book) {
        if (!approvedReservation.containsKey(book)) {
            return;
        } else {
            approvedReservation.remove(book);
        }
    }

//    public void removeItem(Product product, int quantity) {
//        if (!shoppedProducts.containsKey(product)) {
//            return;
//        }
//        int oldQuantity = shoppedProducts.get(product);
//        int newQuantity = oldQuantity - quantity;
//        if (newQuantity > 0) {
//            shoppedProducts.put(product, newQuantity);
//        } else {
//            shoppedProducts.remove(product);
//        }
//    }
    public void empty() {
        approvedReservation.clear();
    }

}
