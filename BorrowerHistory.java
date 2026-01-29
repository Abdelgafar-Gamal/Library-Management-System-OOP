import java.util.List;
import java.util.Scanner;

public class BorrowerHistory extends Customer {

    private List<Book> borrowedBooksHistory;

  /*  public BorrowerHistory(String id, String name, String password) {
        super(id, name, password);
        this.borrowedBooksHistory = new ArrayList<>();
    }

    public List<Book> getBorrowedBooksHistory() {
        return borrowedBooksHistory;
    }*/





    public static void returnBook(Library library, Customer customer, Scanner scanner) {

        System.out.print("Enter Book ID to Return: ");

        String bookId = scanner.nextLine();

        Book book = Reader.searchforbookid(bookId);


        // بيتحقق لو الكتاب موجود وكمان مستعار (يعني الاتنين مع بعض)
        if (book != null) {
            if (book.getStatus().equalsIgnoreCase("borrowed")) {

                Writer.returnbook(book.getBookid(), customer.getId());

                Writer.changeBookStatus(book, "available");

                UserManager.add_rating_and_review(book, scanner);

                System.out.println("Thank You!");

            } else {
                System.out.println("This book is not currently borrowed.");
            }
        }
    }









    @Override
    public void borrowBook(Book book) {
        super.borrowBook(book);
        borrowedBooksHistory.add(book);
    }

  /*  public String getHistory() {
        if (borrowedBooksHistory.isEmpty()) {
            return "No borrowed books history.";
        }
        else {

            StringBuilder history = new StringBuilder("Borrowed Books History:\n");
            for (Book book : borrowedBooksHistory) {
                history.append(book.getTitle()).append("\n");
            }
            return history.toString();
        }
    }*/
}
