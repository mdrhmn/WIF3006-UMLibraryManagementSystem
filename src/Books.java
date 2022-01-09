
public class Books {
    String title;
    String author;
    int year;
    boolean issuedStatus;
    int quantity;
    
    public Books() {}
    
    public Books(String title, String author, int year, int quantity) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
        this.issuedStatus = false;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public boolean getIssuedStatus() {
        return this.issuedStatus;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public void setIssuedStatus(boolean issuedStatus) {
        this.issuedStatus = issuedStatus;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
