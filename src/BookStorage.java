import java.util.ArrayList;


public class BookStorage {
	
	private static BookStorage uniqueInstance;
	ArrayList<Book> books;
	
	
	public BookStorage() {}
	
	
	// SINGLETON 
	public static BookStorage getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new BookStorage();
			
			// TEMP DATA
			uniqueInstance.books = new ArrayList<Book>();
			for(int i=1; i<=9; i++) {
				uniqueInstance.books.add(new Book("Book "+i, "Author "+i, 2000+i, false));
			}	
		}
		return uniqueInstance;
	}
	
	
	public void viewBooks() {
		int count = 0;
		for(Book book : books) {
			System.out.println(count+" : "+book);
			count++;
		}
	}
	
	
	public Book getBook(int index) {
		return books.get(index);
	}
	
}
