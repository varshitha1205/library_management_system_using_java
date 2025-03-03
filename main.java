import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    private Library library;
    private JTextArea textArea;

    public Main() {
        this.library = new Library();
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top panel for title
        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel for book list
        JPanel centerPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea(20, 60);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Left panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBookDialog(frame);
            }
        });
        buttonPanel.add(addBookButton);

        JButton borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        borrowBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowBookDialog(frame);
            }
        });
        buttonPanel.add(borrowBookButton);

        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBookDialog(frame);
            }
        });
        buttonPanel.add(returnBookButton);

        JButton displayBooksButton = new JButton("Display All Books");
        displayBooksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAllBooks();
            }
        });
        buttonPanel.add(displayBooksButton);

        JButton searchButton = new JButton("Search Books");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBooksDialog(frame);
            }
        });
        buttonPanel.add(searchButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.WEST);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addBookDialog(JFrame frame) {
        String title = JOptionPane.showInputDialog(frame, "Enter book title:");
        String author = JOptionPane.showInputDialog(frame, "Enter book author:");
        if (title != null && author != null) {
            Book newBook = new Book(title, author);
            library.addBook(newBook);
            displayAllBooks();
        }
    }

    private void borrowBookDialog(JFrame frame) {
        String title = JOptionPane.showInputDialog(frame, "Enter book title to borrow:");
        if (title != null) {
            library.borrowBook(title);
            displayAllBooks();
        }
    }

    private void returnBookDialog(JFrame frame) {
        String title = JOptionPane.showInputDialog(frame, "Enter book title to return:");
        if (title != null) {
            library.returnBook(title);
            displayAllBooks();
        }
    }

    private void searchBooksDialog(JFrame frame) {
        String searchTerm = JOptionPane.showInputDialog(frame, "Enter search term:");
        if (searchTerm != null) {
            List<Book> searchResults = library.searchBooks(searchTerm);
            updateTextArea(textArea, searchResults);
        }
    }

    private void displayAllBooks() {
        List<Book> books = library.getAllBooks();
        updateTextArea(textArea, books);
    }

    private void updateTextArea(JTextArea textArea, List<Book> books) {
        textArea.setText("");
        for (Book book : books) {
            textArea.append(book.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });	
    }
}
