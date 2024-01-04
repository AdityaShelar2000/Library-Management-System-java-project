package library_management_system;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}

class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

class Transaction {
    private User user;
    private Book book;
    private String date;

    public Transaction(User user, Book book, String date) {
        this.user = user;
        this.book = book;
        this.date = date;
    }

    public void displayTransaction() {
        System.out.println("Transaction Details:");
        System.out.println("User: " + user.getName());
        System.out.println("Book: " + book.getTitle());
        System.out.println("Date: " + date);
    }
}

public class Project {
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        Library library = new Library();

        // Adding some sample books to the library
        library.addBook(new Book(1, "Java Programming", "Hardik Shelar"));
        library.addBook(new Book(2, "Data Structures", "Sagar Gupta"));
        library.addBook(new Book(3, "Algorithm Design", "Aditya Shelar"));

        // Main menu
        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Display Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = S.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    borrowBook(library);
                    break;
                case 3:
                    returnBook(library);
                    break;
                case 4:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void borrowBook(Library library) {
        Scanner s = new Scanner(System.in);

        // Display available books
        library.displayBooks();

        // Enter book ID to borrow
        System.out.print("Enter the ID of the book you want to borrow: ");
        int bookId = s.nextInt();

        Book book = library.findBookById(bookId);

        if (book != null && book.isAvailable()) {
            // Book is available, proceed with borrowing
            System.out.print("Enter your user ID: ");
            int userId = s.nextInt();
            s.nextLine();  // Consume the newline character

            System.out.print("Enter your name: ");
            String userName = s.nextLine();

            User user = new User(userId, userName);
            String currentDate = "2023-12-21";  // You can use a proper date mechanism

            Transaction transaction = new Transaction(user, book, currentDate);

            // Update book availability status
            book.setAvailable(false);

            // Display transaction details
            transaction.displayTransaction();
        } else if (book == null) {
            System.out.println("Book not found. Please enter a valid book ID.");
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    private static void returnBook(Library library) {
        Scanner sc = new Scanner(System.in);

        // Enter book ID to return
        System.out.print("Enter the ID of the book you want to return: ");
        int bookId = sc.nextInt();
        

        Book book = library.findBookById(bookId);

        if (book != null && !book.isAvailable()) {
            // Book is borrowed, proceed with returning
            // Update book availability status
            book.setAvailable(true);

            System.out.println("Book returned successfully.");
        } else if (book == null) {
            System.out.println("Book not found. Please enter a valid book ID.");
        } else {
            System.out.println("Book is already available. No need for return.");
        }
    }
}
