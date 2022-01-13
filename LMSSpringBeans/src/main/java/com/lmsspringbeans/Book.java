package com.lmsspringbeans;

public class Book {

    public String name;
    public String author;
    public int id;
    public int year;
    public int availableQuantity;
    public int unavailableQuantity;
    public static int count = 0;

    public Book() {

    }

    public Book(String name, String author, int year, int availableQuantity, int unavailableQuantity) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.availableQuantity = availableQuantity;
        this.unavailableQuantity = unavailableQuantity;
        this.id = count;
        count++;
    }

    public void setBook(String name, String author, int year, int availableQuantity, int unavailableQuantity) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.availableQuantity = availableQuantity;
        this.unavailableQuantity = unavailableQuantity;
        this.id = count;
        count++;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public int getUnavailableQuantity() {
        return unavailableQuantity;
    }

    public static int getCount() {
        return count;
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
