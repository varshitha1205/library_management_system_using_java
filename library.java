import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void borrowBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.borrowBook();
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    public void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.returnBook();
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> searchBooks(String searchTerm) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    private Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }
}
