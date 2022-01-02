
import java.util.HashMap;

public class Reservations {
	
	
	private static Reservations uniqueInstance;
	HashMap<Member, ReservationInfromation> reservationQueue;
	
	// SINGLETON PATTERN
	public static Reservations getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Reservations();
			uniqueInstance.reservationQueue = new HashMap<Member, ReservationInfromation>();
		}
		return uniqueInstance;
	}
	
	// TO FIND MEMBER'S RESERVATION DATA
	public ReservationInfromation myReservation(Member member) {
		ReservationInfromation reservationInformation = uniqueInstance.reservationQueue.get(member);
		if(reservationInformation == null) {
			reservationInformation = new ReservationInfromation();
			uniqueInstance.reservationQueue.put(member, reservationInformation);
		}
		return reservationInformation;
	}
	
	// TO MAKE A RESERVATION
	public void makeReservation(Member member, Book book) {
		ReservationInfromation reservationInformation = uniqueInstance.myReservation(member);
		if(reservationInformation.bookRequested.contains(book)) {
			System.out.println("ERROR: BOOK ALREADY IN YOUR RESERVATION");
		}else {
			reservationInformation.bookRequested.add(book);			
		}
		System.out.println(reservationInformation);
	}
	
	// TO DELETE RESERVATION DATA
	public void cancelReservation(Member member) {
		if(uniqueInstance.myReservation(member) != null) {
			uniqueInstance.reservationQueue.remove(member);
			System.out.println("SUCCESS: RESERVATION DATA DELETED");
		}else {
			System.out.println("ERROR: YOU HAVE NO RESERVATION DATA");
		}
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("RESERVATION QUEUE\n");
		buffer.append("-----------------\n");
		
		int count = 0;
		ReservationInfromation ri;
		for(Member m : uniqueInstance.reservationQueue.keySet()) {
			buffer.append(count+"\t"+m.name+"\n");
			
			ri = uniqueInstance.reservationQueue.get(m);
			
			for(Book b : ri.bookRequested) {
				buffer.append("\t-"+b+"\n");				
			}
			count++;
		}
		buffer.append("-----------------\n");
		
		return buffer.toString();
		
		
		
	}
	
	
	
	
	
}
