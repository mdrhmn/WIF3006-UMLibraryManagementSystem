
public class Book {
	String name;
	String author;
	int year;
//	Boolean issuedStatus;
	int availableQuantity;
	int unavailableQuantity;
	
	
	public Book(
		String name, 
		String author, 
		int year, 
		int availableQuantity, 
		int unavailableQuantity
	) {
		
		this.name = name;
		this.author = author;
		this.year = year;
		this.availableQuantity = availableQuantity;
		this.unavailableQuantity = unavailableQuantity;
		
	}
	
	
	@Override
	public String toString() {		
		return "["+name+" | "+author+" | "+year+"| Available: "+availableQuantity+"| Unavailable: "+unavailableQuantity+"]";
	}
}
