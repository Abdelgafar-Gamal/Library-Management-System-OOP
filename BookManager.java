import java.util.*;

public class BookManager  {
    private  List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>();
    }





   /* public List<Book> getAllBooks() {

        return new ArrayList<>(books);
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getStatus().equalsIgnoreCase("available")) {
                availableBooks.add(book);

            }
        }
        return availableBooks;
    }

    public Book findBookById(String id) {

        for (Book book : books) {
            if (book.getBookid().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchBooks(String term) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(term.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(term.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }*/

    public static void addBook(Library l, Scanner s) {
        System.out.print("Enter Book ID: ");
        String bookId = s.nextLine();
        System.out.print("Enter Title: ");
        String title = s.nextLine();
        System.out.print("Enter Author: ");
        String author = s.nextLine();
        System.out.print("Enter Publication Year: ");
        int year = s.nextInt();
        System.out.print("Enter Price: ");
        double price = s.nextDouble();
        s.nextLine();

        Book newaddedbook = new Book(bookId,title,author,year,price,"available");

        Writer.addbook(newaddedbook);

        System.out.println("Book added successfully.");
    }

    public static void viewAllBooks(Library library) {
        List<Book> books = Reader.readBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println(book);

            }

        }
    }

    public static void viewavailablebooks(Library l) {
        List<Book> books = Reader.readAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println(book);
            }

        }
    }


    public static void viewborrowedbooks(Library l) {
        List<Book> books = Reader.viewborrowedbooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println("Book ID: " + book.getBookid() + " | " + "Borrowed By: " + book.getTitle());
            }

        }
    }


    public static void viewbuyhistory(Library l) {
        List<Book> books = Reader.viewbought();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println(book);
            }

        }
    }
    public static void viewborrowhistory(Library l,Scanner s) {
        List<Book> books = Reader.viewborrowhistory();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println("Book ID: " + book.getBookid() + " | "+ "Borrowed By: " + book.getTitle());
            }

        }
    }



    public static void removeBook(Library l, Scanner s) {
        System.out.print("Enter Book ID to Remove: ");
        String bookId = s.nextLine();
        Writer.removeBook(bookId);

    }
}