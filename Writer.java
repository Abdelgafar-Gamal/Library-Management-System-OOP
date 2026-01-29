import java.io.*;
import java.util.*;


public class Writer {
    private static final String bookfile = "books.txt";
    private static final String customerfile = "customer.txt";
    private static final String borrowedfile = "borrowed.txt";
    private static final String borrowhistoryfile = "borrow history.txt";
    private static final String buyhistoryfile = "buy history.txt";


    public static void removeBook(String bookid) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(bookfile))) {
            String line;
            Book currentBook = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Book ID: ")) {
                    if (currentBook != null) {
                        books.add(currentBook);
                    }
                    String id = line.substring("Book ID: ".length());
                    currentBook = new Book();
                    currentBook.setBookid(id); // Set the book ID
                } else if (currentBook != null) { // Only process lines if currentBook is not null
                    if (line.startsWith("Title: ")) {
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
                                currentBook.addReview(review, 0); // Assuming a default rating of 0 for the review
                            }
                        }
                    }
                }
            }

            // Add the last book if it exists
            if (currentBook != null) {
                books.add(currentBook);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
            return; // Exit the method if reading fails
        }



        books.removeIf(book -> book.getBookid().equals(bookid));




        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookfile))) {
            for (Book book : books) {
                writer.write("Book ID: " + book.getBookid());
                writer.newLine();
                writer.write("Title: " + book.getTitle());
                writer.newLine();
                writer.write("Author: " + book.getAuthor());
                writer.newLine();
                writer.write("Publication Year: " + book.getPublicationYear());
                writer.newLine();
                writer.write("Price: " + book.getPrice());
                writer.newLine();
                writer.write("Status: " + book.getStatus());
                writer.newLine();
                writer.write("Rating: " + book.getRating());
                writer.newLine();
                writer.write("Customer Review Count: " + book.getReviews().size());
                writer.newLine();
                for (String review : book.getReviews()) {
                    writer.write("Review: " + review);
                    writer.newLine();
                }
                writer.newLine(); // Add an extra newline for separation between books
            }
            System.out.println("Book with ID " + bookid + " has been removed.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the book file: " + e.getMessage());
        }

    }

    public static void addbook(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookfile, true))) {
            writer.write("Book ID: " + book.getBookid());
            writer.newLine();
            writer.write("Title: " + book.getTitle());
            writer.newLine();
            writer.write("Author: " + book.getAuthor());
            writer.newLine();
            writer.write("Publication Year: " + book.getPublicationYear());
            writer.newLine();
            writer.write("Price: " + book.getPrice());
            writer.newLine();
            writer.write("Status: " + book.getStatus());
            writer.newLine();
            writer.write("Rating: " + book.getRating());
            writer.newLine();
            writer.write("Customer Review Count: " + book.getReviews().size());
            writer.newLine();
            for (String review : book.getReviews()) {
                writer.write("Review: " + review);
                writer.newLine();
            }
            writer.newLine();
            writer.newLine();
            System.out.println("Book saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the book: " + e.getMessage());
        }
    }


    public static void addnewcustomer(Customer customer){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerfile, true))) {
            writer.write("Customer ID: " + customer.getId());
            writer.newLine();
            writer.write("Customer Name: " + customer.getName());
            writer.newLine();
            writer.write("Customer Password: " + customer.getPassword());
            writer.newLine();


            System.out.println("New user is added.");
        } catch (IOException e) {
            System.out.println("Failed to register." + e.getMessage());
        }
    }



    public static void borrowbook (Book book, String customerID) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(borrowedfile, true))) {
            writer.write("Book ID: " + book.getBookid());
            writer.newLine();
            writer.write("Customer ID: " + customerID);
            writer.newLine();

            System.out.println("Book saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the book: " + e.getMessage());
        }
    }

    public static boolean returnbook(String bookId, String customerId) {
        List<String> lines = new ArrayList<>();
        boolean bookFound = false;

        // Read the file and store lines in a list
        try (BufferedReader reader = new BufferedReader(new FileReader(borrowedfile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the current line is "Book ID: <bookId>"
                if (line.equals("Book ID: " + bookId)) {
                    // Read the next line to check for the corresponding Customer ID
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.equals("Customer ID: " + customerId)) {
                        bookFound = true; // Mark that we found the book
                        // Skip the next line (Customer ID) to remove the pair
                    } else {
                        // If the next line doesn't match, add both lines back to the list
                        lines.add(line);
                        if (nextLine != null) {
                            lines.add(nextLine);
                        }
                    }
                } else {
                    // If the line is not a Book ID, just add it back to the list
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false if there was an error reading the file
        }

        // If the book was found, write the updated list back to the file
        if (bookFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(borrowedfile))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                return true; // Return true if the book was successfully removed
            } catch (IOException e) {
                e.printStackTrace();
                return false; // Return false if there was an error writing to the file
            }
        }

        return false; // Return false if the book was not found
    }


    public static void borrowhistory (Book book, String customerid) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(borrowhistoryfile, true))) {
            writer.write("Book ID: " + book.getBookid());
            writer.newLine();
            writer.write("Customer ID: " + customerid);
            writer.newLine();
            System.out.println("Book saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the book: " + e.getMessage());
        }
    }
    public static void changeBookStatus(Book bookId, String newStatus) {
        List<Book> books = new ArrayList<>();
        Book currentBook = null;

        // Read existing books from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(bookfile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Book ID: ")) {
                    // If we have a current book, add it to the list
                    if (currentBook != null) {
                        books.add(currentBook);
                    }
                    // Create a new book
                    currentBook = new Book();
                    String id = line.substring("Book ID: ".length());
                    currentBook.setBookid(id);
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

            // Add the last book if it exists
            if (currentBook != null) {
                books.add(currentBook);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
            return;
        }

        // Update the status of the specified book
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getBookid().equals(bookId.getBookid())) {
                book.setStatus(newStatus);
                bookFound = true;
                break;
            }
        }

        // Write the updated list of books back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookfile))) {
            for (Book book : books) {
                writer.write("Book ID: " + book.getBookid());
                writer.newLine();
                writer.write("Title: " + book.getTitle());
                writer.newLine();
                writer.write("Author: " + book.getAuthor());
                writer.newLine();
                writer.write("Publication Year : " + book.getPublicationYear());
                writer.newLine();
                writer.write("Price: " + book.getPrice());
                writer.newLine();
                writer.write("Status: " + book.getStatus());
                writer.newLine();
                writer.write("Rating: " + book.getRating());
                writer.newLine();
                writer.write("Customer Review Count: " + book.getRatingCount());
                writer.newLine();
                for (String review : book.getReviews()) {
                    writer.write("Review: " + review);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the book file: " + e.getMessage());
        }

        if (bookFound) {
            System.out.println("Book status updated successfully.");
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }

    public static void boughthistory(Book book,String customerid) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(buyhistoryfile,true))) {

            writer.write("Book ID: " + book.getBookid());
            writer.newLine();
            writer.write("Title: " + book.getTitle());
            writer.newLine();
            writer.write("Author: " + book.getAuthor());
            writer.newLine();
            writer.write("Publication Year: " + book.getPublicationYear());
            writer.newLine();
            writer.write("Price: " + book.getPrice());
            writer.newLine();
            writer.write("Customer ID: " + customerid);
            writer.newLine();
            writer.write("Rating: " + book.getRating());
            writer.newLine();

            // Write reviews
            for (String review : book.getReviews()) {
                writer.write("Review: " + review);
                writer.newLine();
            }
            writer.newLine(); // Add an empty line between books

            System.out.println("Books have been written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the book file: " + e.getMessage());
        }
    }


    public static void saveRatingAndReview(Book book, double userRating, String review) {
        List<String> lines = new ArrayList<>();
        boolean bookFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(bookfile))) {
            String line;
            StringBuilder currentBook = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Check if the current line starts a new book entry
                if (line.startsWith("Book ID: ")) {
                    // If we have built a current book entry, process it
                    if (currentBook.length() > 0) {
                        // Check if the current book matches the book we want to update
                        if (currentBook.toString().contains("Book ID: " + book.getBookid())) {
                            bookFound = true; // Mark that we found the book

                            // Update the rating and review count
                            double currentRating = book.getRating();
                            int currentReviewCount = book.getRatingCount();
                            double newRating = (currentRating * currentReviewCount + userRating) / (currentReviewCount + 1);
                            book.setRating(newRating); // Update the book's rating
                            book.setRatingCount(currentReviewCount + 1); // Increment the review count

                            // Append the new review to the current book entry
                            currentBook.append("Review: ").append(review).append(System.lineSeparator());
                            currentBook.append("Rating: ").append(newRating).append(System.lineSeparator());
                            currentBook.append("Customer Review Count: ").append(book.getRatingCount()).append(System.lineSeparator());
                        }
                        // Add the current book entry to the lines list
                        lines.add(currentBook.toString());
                        currentBook.setLength(0); // Clear the current book entry for the next book
                    }
                }
                // Append the current line to the current book entry
                currentBook.append(line).append(System.lineSeparator());
            }

            // Add the last book entry if it exists
            if (currentBook.length() > 0) {
                lines.add(currentBook.toString());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the book file: " + e.getMessage());
            return; // Exit the method if an error occurs
        }

        // Write the updated books back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookfile))) {
            for (String bookEntry : lines) {
                writer.write(bookEntry);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the book file: " + e.getMessage());
        }

        // Provide feedback to the user
        if (bookFound) {
            System.out.println("Rating and review for book ID " + book.getBookid() + " have been saved.");
        } else {
            System.out.println("Book with " + book.getBookid() + " not found.");
        }
    }


}