package model;

public class Book {
    private String title;
    private String category;
    private double price;

    public Book(String title, String category, double price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
    public String getTitle() {
        return title;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toFileString(){
        return title + "," + category + "," + price;
    }

    @Override
    public String toString(){
        return "Title: "+title + "| Category: " + category +" | Price: " + price;
    }
}
