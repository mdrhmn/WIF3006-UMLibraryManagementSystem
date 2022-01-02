import java.util.HashMap;

public class IssuedBooks {
	
	private static IssuedBooks uniqueInstance;	
	HashMap<Member, ReservationInfromation> approvedReservation;
	
	// SINGLETON PATTERN
	public static IssuedBooks getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new IssuedBooks();
			uniqueInstance.approvedReservation = new HashMap<>();
		}
		return uniqueInstance;
	}
	
	// TO GET MEMBER DATA FROM AN INDEX VALUE
	public Member getMember(int memberIndex) {
		Reservations reservation = (new Reservations()).getInstance();
		int count = 0;
		
		for(Member member: reservation.reservationQueue.keySet()) {
			if(count == memberIndex) {
				return member;
			}else {
				count++;
			}
		}
		return null;
	}
		
	
	// TO VIEW CURRENT RESERVATION QUEUE
	public void viewReservationQueue() {
		Reservations reservation = (new Reservations()).getInstance();
		System.out.println(reservation);
	}
	
	
	// TO ISSUE BOOK
	public void issueBooks(Member member) {
		Reservations reservation = (new Reservations()).getInstance();
		
		ReservationInfromation reservationInformation = reservation.reservationQueue.get(member);
		reservation.reservationQueue.remove(member);
		
		uniqueInstance.approvedReservation.put(member, reservationInformation);
		reservationInformation.changeBooksStatus(true);
		
		System.out.println("SUCCESS: MEMBER ["+member.name+"] RESERVARION REQUEST APPROVED");
	}
	
	public void viewIssuedBooks() {
		System.out.println(uniqueInstance.toString());
	}
	
	public void updateIssuedBooks() {}
	
	public void returnIssuedBooks() {}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("ISSUED BOOKS\n");
		buffer.append("-----------------\n");
		
		int count = 0;
		ReservationInfromation reservationInformation;
		for(Member member : uniqueInstance.approvedReservation.keySet()) {
			buffer.append(count+"\t"+member.name+"\n");
			
			reservationInformation = uniqueInstance.approvedReservation.get(member);
			
			for(Book book : reservationInformation.bookRequested) {
				buffer.append("\t-"+book+"\n");				
			}
			
			count++;
		}
		buffer.append("-----------------\n");
		
		return buffer.toString();
	}
}
