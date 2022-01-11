import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Member member1 = new Member("Ajwad Alias", "ajwad", "1234");
		Member member2 = new Member("Ashraf Alias","ashraf", "qwer");		
		Librarian librarian = new Librarian();
		
		Scanner scan = new Scanner(System.in);
		int action;
		boolean exitFlag = false;
		
		do {
			System.out.println("Select a user:");
			System.out.println("[1] Member 1 ("+member1.name+")");
			System.out.println("[2] Member 2 ("+member2.name+")");
			System.out.println("[3] Librarian");
			System.out.println("[99] EXIT PROGRAM");
			
			action = scan.nextInt();
			
			switch (action) {
			case 1: {
				memberActions(member1);
				break;
			}
			case 2: {
				memberActions(member2);
				break;
			}
			case 3: {
				librarianActions(librarian);
				break;
			}
			case 99: {
				exitFlag = true;
				break;
			}
			default:
//				throw new IllegalArgumentException("Unexpected value: " + action);
				System.err.println("Please choose again");
			}
			
			
			if (exitFlag) {
				break;
			}
			
			
			
		}while(true);
		
		
		
		
	}
	
	
	private static void memberActions(Member m) {
		Scanner scan = new Scanner(System.in);
		int action;
		boolean exitFlag = false;
		do {
			System.out.println("Select an action (MEMBER: "+m.name+"): ");
			System.out.println("[1] Reserve a book");
			System.out.println("[2] Cancel my reservation");
			System.out.println("[3] View Books");
			System.out.println("[99] SWITCH USER");
			
			action = scan.nextInt(); 
			
			switch (action) {
			case 1: {
				m.reserveBooks();
				break;
			}
			case 2: {
				m.cancelReserveBooks();
				break;
			}
			case 3: {
				m.viewBooks();
				break;
			}
			case 99: {
				exitFlag = true;
				break;
			}
			default:
//				throw new IllegalArgumentException("Unexpected value: " + action);
				System.err.println("Please choose again");
			}
			
			if (exitFlag) {
				break;
			}
			
		}while(true);
	}
	
	private static void librarianActions(Librarian l) {
		Scanner scan = new Scanner(System.in);
		int action;
		boolean exitFlag = false;
		do {
			System.out.println("Select an action (LIBRARIAN):");
			System.out.println("[1] Issue Books");
			System.out.println("[2] View Issued Books");
			System.out.println("[99] SWITCH USER");
			
			action = scan.nextInt(); 
			
			switch (action) {
			case 1: {
				l.issueBook();
				break;
			}
			case 2: {
				l.viewIssuedBooks();
				break;
			}
			case 99: {
				exitFlag = true;
				break;
			}
			default:
//				throw new IllegalArgumentException("Unexpected value: " + action);
				System.err.println("Please choose again");
			}
			
			if (exitFlag) {
				break;
			}
			
		}while(true);
	}

}
