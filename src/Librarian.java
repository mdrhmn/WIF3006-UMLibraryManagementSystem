import java.util.Scanner;

public class Librarian {
	
	
	public void issueBook() {
		IssuedBooks issuedBooks = (new IssuedBooks()).getInstance();
		
		System.out.println("\n -- SELECT A USER USING ITS [INDEX] -- \n");
		issuedBooks.viewReservationQueue();
		
		System.out.print("Request to issue [99 to cancel]: ");
		
		Scanner scanner = new Scanner(System.in);
		
		// ISSUE THE BOOK
		int memberIndex = scanner.nextInt();
		if(memberIndex != 99) {
			issuedBooks.issueBooks(issuedBooks.getMember(memberIndex));			
		}
		
	}
	
	public void viewIssuedBooks() {
		IssuedBooks issuedBooks = (new IssuedBooks()).getInstance();
		System.out.println(issuedBooks);
	}
	
	public void updateIssuedBooks() {
		
	}
	
	
}
