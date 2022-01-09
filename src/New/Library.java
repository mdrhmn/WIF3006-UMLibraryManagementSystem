package New;


import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Faidz
 */
public class Library {

    BookStorage bookStorage = new BookStorage();
    IssuedBook issuedBook = new IssuedBook();
    
    public Library() {
        init();
    }

    public void init() {
        Books book1 = new Books("The Lightning Thief","Rick Riordan", 2003, 4);
        bookStorage.registerBook(book1);
        
        Books book2 = new Books("The Bad Beginning","Lemony Snicket", 2003, 4);
        bookStorage.registerBook(book2);
    }
    
    public List<Books> viewBooks() {
        return bookStorage.viewBook();
    }
    
    public Map<Books, String> viewIssuedBook() {
        return issuedBook.viewIssuedBook();
    }

}
