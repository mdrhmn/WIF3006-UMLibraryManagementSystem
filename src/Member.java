import java.util.Scanner;

public class Member {
	
	String name;
	String username;
	String password;
	
	public Member(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	public void returnIssuedBooks() {
		
	}
	
	public void viewBooks() {
		BookStorage bs = (new BookStorage()).getInstance();
		bs.viewBooks();
	}
	
	
	public void reserveBooks() {
		BookStorage bs = (new BookStorage()).getInstance();
		Reservations r = (new Reservations()).getInstance();
		System.out.println("\n -- SELECT A BOOK USING ITS [INDEX] -- \n");
		bs.viewBooks();
		
		System.out.print("Book to add [99 to cancel]: ");
		
		Scanner scanner = new Scanner(System.in);
		
		//reserve book
		int bookIndex = scanner.nextInt();
		if(bookIndex != 99) {
			r.makeReservation(this, bs.getBook(bookIndex));			
		}
	}
	
	public void cancelReserveBooks() {
		Reservations r = (new Reservations()).getInstance();
		r.cancelReservation(this);
	}
	
	
}
