
public class Book {
	String name;
	String author;
	int year;
	Boolean issuedStatus;
	
	
	public Book(String name, String author, int year, Boolean issuedStatus) {
		this.name = name;
		this.author = author;
		this.year = year;
		this.issuedStatus = issuedStatus;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+name+" | "+author+" | "+year+(issuedStatus? " | Unavailable" : " | Avaliable")+"]";
	}
}
