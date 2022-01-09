
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStorage {

//    Map<Books, Integer> books = new HashMap<Books, Integer>();
    ArrayList<Books> existingBooks = new ArrayList<Books>();

    public void registerBook(Books book) {
        existingBooks.add(book);
    }

    public void updateBook() {
    }

    public void deleteBook(int bookIndex) {
        existingBooks.remove(bookIndex-1);
//        if(!existingBooks.contains(book)){
//            return;
//        } else {
//            existingBooks.remove(book);
//        }
//        if (!books.containsKey(book)) {
//            return;
//        } else {
//            books.remove(book);
//        }
        
    }

    public ArrayList<Books> viewBook() {
        return existingBooks;
    }
}
