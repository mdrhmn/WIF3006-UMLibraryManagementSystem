import java.util.Scanner;

public class Member {
	
	String name;
	
	public Member(String name) {
		this.name = name;
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
		
		System.out.print("Book to add: ");
		
		Scanner scanner = new Scanner(System.in);
		
		//reserve book
		int bookIndex = scanner.nextInt();
		r.makeReservation(this, bs.getBook(bookIndex));
	}
	
	public void cancelReserveBooks() {
		Reservations r = (new Reservations()).getInstance();
		r.cancelReservation(this);
	}
	
	
}
