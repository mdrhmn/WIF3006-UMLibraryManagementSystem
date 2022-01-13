package com.lmsspringbeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookStorage {

    public static ArrayList<Book> books = new ArrayList<>();
    ApplicationContext context;

    public BookStorage() {
    }

    public void setApplicationContext(ApplicationContext context) {
        if (this.context == null) {
            System.err.println("BOOK STORAGE: CONTEXT PASSED");
            this.context = (ApplicationContext) context;

            for (int i = 1; i <= 5; i++) {
                Book book = (Book) context.getBean("book");
                book = new Book("Book " + i, "Author " + i, 2000 + i, 3, 0);
                books.add(book);
            }
        }
    }

    public void updateBook(int bookID, String bookName, String bookAuthor, int bookYear, int bookQuantity) {
        Book selectedBook = getBookByID(bookID);
        selectedBook.setTitle(bookName);
        selectedBook.setAuthor(bookAuthor);
        selectedBook.setYear(bookYear);
        selectedBook.setAvailableQuantity(bookQuantity);
        System.out.println(bookName + " successfully updated.");

    }

    public void addBook(Book newBook) {
        books.add(newBook);
        System.out.println(newBook.name + " successfully added.");

    }

    public void deleteBook(int bookID) {
        Book deletedBook = getBookByID(bookID);
        books.remove(bookID);
        System.out.println(deletedBook.name + " successfully deleted.");
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book getBookByID(int bookID) {
        for (Book book : books) {
            if (book.id == bookID) {
                return book;
            }
        }
        return null;
    }

    public Book getBookByIndex(int index) {
        return books.get(index);
    }

    public int getBookIndexById(int bookID) {
        int count = 0;

        for (Book book : books) {
            if (book.id == bookID) {
                return count;
            }
            count++;
        }
        return 0;
    }

}
