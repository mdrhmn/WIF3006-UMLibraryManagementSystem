
import java.util.ArrayList;

public class ReservationInfromation {
	ArrayList<Book> bookRequested;	
	
	public ReservationInfromation() {
		bookRequested = new ArrayList<Book>();
	}
	
	// TO CHANGE BOOK ISSUE STATUS
	public void changeBooksStatus(boolean status) {
		for(Book book: bookRequested) {
			book.issuedStatus = status;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		StringBuffer buffer = new StringBuffer();

		buffer.append("\nBELOW ARE THE BOOK YOU WANT TO RESERVE:\n");
		buffer.append("---------------------------------------\n\n");
		for(Book book : bookRequested) {
			buffer.append(book+"\n");
		}
		buffer.append("\n---------------------------------------\n");
		return buffer.toString();
	}
	
	
	
	
	
}
