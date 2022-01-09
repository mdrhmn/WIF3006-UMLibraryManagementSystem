
public class Book {

    static int count = 0;
    String name;
    String author;
    int id;
    int year;
    int availableQuantity;
    int unavailableQuantity;

    public Book(String name, String author, int year, int availableQuantity, int unavailableQuantity) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.availableQuantity = availableQuantity;
        this.unavailableQuantity = unavailableQuantity;
        this.id = count;
        count++;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setUnvailableQuantity(int unavailableQuantity) {
        this.unavailableQuantity = unavailableQuantity;
    }

}
