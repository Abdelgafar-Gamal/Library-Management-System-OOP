import java.io.*;
import java.util.*;


public class Reader {
    private static final String bookfile = "books.txt";
    private static final String customerfile = "customer.txt";
    private static final String borrowedfile = "borrowed.txt";
    private static final String borrowhistoryfile = "borrow history.txt";
    private static final String buyhistoryfile = "buy history.txt";


    public static List<Book> readBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(bookfile))) {
            String line;
            Book currentBook = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Book ID: ")) {
                    if (currentBook != null) {
                        books.add(currentBook);
                    }
                    String bookId = line.substring("Book ID: ".length());
                    currentBook = new Book();
                    currentBook.setBookid(bookId);
                } else if (line.startsWith("Title: ")) {
                    currentBook.setTitle(line.substring("Title: ".length()));
                } else if (line.startsWith("Author: ")) {
                    currentBook.setAuthor(line.substring("Author: ".length()));
                } else if (line.startsWith("Publication Year: ")) {
                    int year = Integer.parseInt(line.substring("Publication Year: ".length()));
                    currentBook.setPublicationYear(year);
                } else if (line.startsWith("Price: ")) {
                    double price = Double.parseDouble(line.substring("Price: ".length()));
                    currentBook.setPrice(price);
                } else if (line.startsWith("Status: ")) {
                    currentBook.setStatus(line.substring("Status: ".length()));
                } else if (line.startsWith("Rating: ")) {
                    double rating = Double.parseDouble(line.substring("Rating: ".length()));
                    currentBook.setRating(rating);
                } else if (line.startsWith("Customer Review Count: ")) {
                    int reviewCount = Integer.parseInt(line.substring("Customer Review Count: ".length()));

                    for (int i = 0; i < reviewCount; i++) {
                        String reviewLine = reader.readLine(); // Read the review
                        if (reviewLine != null && reviewLine.startsWith("Review: ")) {
                            String review = reviewLine.substring("Review: ".length());
                            currentBook.addReview(review, 0);
                        }
                    }
                }
            }


            if (currentBook != null) {
                books.add(currentBook);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
        }

        return books;
    }

    public static List<Book> readAvailableBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(bookfile))) {
            String line;
            Book currentBook = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Book ID: ")) {
                    // If we have a current book, check its status and add it to the list if available
                    if (currentBook != null && "available".equalsIgnoreCase(currentBook.getStatus())) {
                        books.add(currentBook);
                    }
                    // Create a new book
                    currentBook = new Book();
                    String bookId = line.substring("Book ID: ".length());
                    currentBook.setBookid(bookId);
                } else if (line.startsWith("Title: ")) {
                    if (currentBook != null) {
                        currentBook.setTitle(line.substring("Title: ".length()));
                    }
                } else if (line.startsWith("Author: ")) {
                    if (currentBook != null) {
                        currentBook.setAuthor(line.substring("Author: ".length()));
                    }
                } else if (line.startsWith("Publication Year: ")) {
                    if (currentBook != null) {
                        int year = Integer.parseInt(line.substring("Publication Year: ".length()));
                        currentBook.setPublicationYear(year);
                    }
                } else if (line.startsWith("Price: ")) {
                    if (currentBook != null) {
                        double price = Double.parseDouble(line.substring("Price: ".length()));
                        currentBook.setPrice(price);
                    }
                } else if (line.startsWith("Status: ")) {
                    if (currentBook != null) {
                        currentBook.setStatus(line.substring("Status: ".length()));
                    }
                } else if (line.startsWith("Rating: ")) {
                    if (currentBook != null) {
                        double rating = Double.parseDouble(line.substring("Rating: ".length()));
                        currentBook.setRating(rating);
                    }
                } else if (line.startsWith("Customer Review Count: ")) {
                    if (currentBook != null) {
                        int reviewCount = Integer.parseInt(line.substring("Customer Review Count: ".length()));
                        for (int i = 0; i < reviewCount; i++) {
                            String reviewLine = reader.readLine(); // Read the review
                            if (reviewLine != null && reviewLine.startsWith("Review: ")) {
                                String review = reviewLine.substring("Review: ".length());
                                currentBook.addReview(review, 0);
                            }
                        }
                    }
                }
            }

            // Check the last book after the loop
            if (currentBook != null && "available".equalsIgnoreCase(currentBook.getStatus())) {
                books.add(currentBook);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
        }

        return books;
    }

    public static List<Book> viewborrowedbooks() {
        List<Book> books = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(borrowedfile))) {
            String line;
            Book currentBook = null;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Book ID: ")) {
                    if (currentBook != null) {
                        books.add(currentBook);
                    }
                    String bookId = line.substring("Book ID: ".length());
                    currentBook = new Book();
                    currentBook.setBookid(bookId);
                } else if (line.startsWith("Customer ID: ")) {
                    currentBook.setTitle(line.substring("Customer ID: ".length()));

                }

            }


            if (currentBook != null) {
                books.add(currentBook);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
        }

        return books;
    }

    public static List<Book> viewbought() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(buyhistoryfile))) {
            String line;
            Book currentBook = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Book ID: ")) {
                    if (currentBook != null) {
                        books.add(currentBook);
                    }
                    String bookId = line.substring("Book ID: ".length());
                    currentBook = new Book();
                    currentBook.setBookid(bookId);
                } else if (line.startsWith("Title: ")) {
                    currentBook.setTitle(line.substring("Title: ".length()));
                } else if (line.startsWith("Author: ")) {
                    currentBook.setAuthor(line.substring("Author: ".length()));
                } else if (line.startsWith("Publication Year: ")) {
                    int year = Integer.parseInt(line.substring("Publication Year: ".length()));
                    currentBook.setPublicationYear(year);
                } else if (line.startsWith("Price: ")) {
                    double price = Double.parseDouble(line.substring("Price: ".length()));
                    currentBook.setPrice(price);
                } else if (line.startsWith("Customer ID: ")) {
                    currentBook.setStatus(line.substring("Customer ID: ".length()));
                } else if (line.startsWith("Rating: ")) {
                    double rating = Double.parseDouble(line.substring("Rating: ".length()));
                    currentBook.setRating(rating);

                }

            }


            if (currentBook != null) {
                books.add(currentBook);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
        }

        return books;
    }

    public static List<Book> viewborrowhistory() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(borrowhistoryfile))) {
            String line;
            Book currentBook = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Book ID: ") ) {
                    if (currentBook != null) {
                        books.add(currentBook);
                    }
                    String bookId = line.substring("Book ID: ".length());
                    currentBook = new Book();
                    currentBook.setBookid(bookId);
                } else if (line.startsWith("Customer ID: ")) {
                    currentBook.setTitle(line.substring("Customer ID: ".length()));

                }
            }

            if (currentBook != null) {
                books.add(currentBook);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
        }

        return books;
    }

    public void readborrowhistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(borrowhistoryfile))) {
            String line;
            String bookId = null;
            String customerId = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Book ID: ")) {
                    bookId = line.substring(9).trim(); // Extract book ID
                } else if (line.startsWith("Customer ID: ")) {
                    customerId = line.substring(14).trim(); // Extract customer ID
                }

                // If both bookId and customerId are found, create a Book object
                if (bookId != null && customerId != null) {
                    Book book = new Book();
                    bookId = null; // Reset bookId for the next entry
                    customerId = null; // Reset customerId for the next entry
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> showallcustomers() {
        List<Customer> customerList = new ArrayList<>(); // Local list to hold customers
        try (BufferedReader reader = new BufferedReader(new FileReader(customerfile))) {
            String line;
            String id = null;
            String name = null;
            String password = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Customer ID: ")) {
                    // If we have a complete customer, add it to the list
                    if (id != null && name != null && password != null) {
                        customerList.add(new Customer(id, name, password));
                    }
                    // Start a new customer entry
                    id = line.substring("Customer ID: ".length()).trim();
                } else if (line.startsWith("Customer Name: ")) {
                    name = line.substring("Customer Name: ".length()).trim();
                } else if (line.startsWith("Customer Password: ")) {
                    password = line.substring("Customer Password: ".length()).trim();
                }
            }

            // Add the last customer if the file ends without a new customer ID
            if (id != null && name != null && password != null) {
                customerList.add(new Customer(id, name, password));
            }
        } catch (IOException e) {
            System.out.println("Failed to load customers: " + e.getMessage());
        }
        return customerList; // Return the list of customers
    }



    public static Book searchforbookid(String bookid) {
        Book currentBook = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(bookfile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Book ID: ")) {
                    // If we have a current book, check if it matches the requested book ID
                    if (currentBook != null && currentBook.getBookid().equals(bookid)) {
                        return currentBook; // Return the found book
                    }
                    // Create a new book
                    currentBook = new Book();
                    String bookId = line.substring("Book ID: ".length());
                    currentBook.setBookid(bookId);
                } else if (line.startsWith("Title: ")) {
                    if (currentBook != null) {
                        currentBook.setTitle(line.substring("Title: ".length()));
                    }
                } else if (line.startsWith("Author: ")) {
                    if (currentBook != null) {
                        currentBook.setAuthor(line.substring("Author: ".length()));
                    }
                } else if (line.startsWith("Publication Year: ")) {
                    if (currentBook != null) {
                        int year = Integer.parseInt(line.substring("Publication Year: ".length()));
                        currentBook.setPublicationYear(year);
                    }
                } else if (line.startsWith("Price: ")) {
                    if (currentBook != null) {
                        double price = Double.parseDouble(line.substring("Price: ".length()));
                        currentBook.setPrice(price);
                    }
                } else if (line.startsWith("Status: ")) {
                    if (currentBook != null) {
                        currentBook.setStatus(line.substring("Status: ".length()));
                    }
                } else if (line.startsWith("Rating: ")) {
                    if (currentBook != null) {
                        double rating = Double.parseDouble(line.substring("Rating: ".length()));
                        currentBook.setRating(rating);
                    }
                } else if (line.startsWith("Customer Review Count: ")) {
                    if (currentBook != null) {
                        int reviewCount = Integer.parseInt(line.substring("Customer Review Count: ".length()));
                        for (int i = 0; i < reviewCount; i++) {
                            String reviewLine = reader.readLine(); // Read the review
                            if (reviewLine != null && reviewLine.startsWith("Review: ")) {
                                String review = reviewLine.substring("Review: ".length());
                                currentBook.addReview(review, 0);
                            }
                        }
                    }
                }
            }

            // Final check for the last book
            if (currentBook != null && currentBook.getBookid().equals(bookid)) {
                return currentBook; // Return the last book if it matches
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
        }

        return null; // Return null if no book was found
    }



}