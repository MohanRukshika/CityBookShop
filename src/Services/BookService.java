package Services;

import model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private static final String FILE_NAME = "books.txt";

    public void addBook(Book book) {
        FileHandler.writeLine(FILE_NAME, book.toFileString());
    }

    public List<Book> getAllBooks() {
        List<String> lines = FileHandler.readAllLines(FILE_NAME);
        List<Book> books = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            String title = parts[0];
            String category = parts[1];
            double price = Double.parseDouble(parts[2]);

            Book b=new Book(title, category, price);

            books.add(b);
        }

        return books;
    }

    public List<Book> searchByName(String name) {
        List<Book> result = new ArrayList<>();

        for (Book book : getAllBooks()) {
            if (book.getTitle().toLowerCase().contains(name.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    public List<Book> searchByCategory(String category) {
        List<Book> result = new ArrayList<>();

        for (Book book : getAllBooks()) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }

        return result;
    }
}
