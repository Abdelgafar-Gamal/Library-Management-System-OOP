import java.util.Scanner;

public class Borrower {
    private String borrowerId;
    private Book book;

   /* public Borrower(String borrowerId, Book book) {
        this.borrowerId = borrowerId;
        this.book = book;

    }*/










    public static void borrowBook(Library library, Customer customer, Scanner scanner) {

        System.out.print("Enter Book ID to Borrow: ");

        String bookId = scanner.nextLine();

        Book book = Reader.searchforbookid(bookId); // Assuming this returns a Book or null

        if (book != null && book.getStatus().equalsIgnoreCase("available")) {

            System.out.println("Choose payment method:");

            System.out.println("1. Cash");

            System.out.println("2. Visa");

            System.out.print("Enter choice: ");

            int paymentChoice = scanner.nextInt();

            scanner.nextLine();

            String paymentMethod = "";

            boolean paymentSuccessful = false;

            if (paymentChoice == 1) {

                paymentMethod = "Cash";

                System.out.println("Payment successful using Cash.");

                paymentSuccessful = true;

                Writer.borrowhistory(book, customer.getId());

                Writer.changeBookStatus(book,"borrowed");

            } else if (paymentChoice == 2) {

                paymentMethod = "Visa";

                boolean isValid = false;

                int attempts = 3; // محاولات الرقم السري

                while (attempts > 0 && !isValid) {

                    System.out.print("Enter Visa PIN (4 digits): ");

                    String pin = scanner.nextLine();

                    // بيتحقق من الرقم السري
                    if (pin.matches("\\d{4}")) {
                        isValid = true;
                        System.out.println("Payment successful using Visa.");
                        paymentSuccessful = true;
                        Writer.borrowhistory(book, customer.getId());
                        Writer.changeBookStatus(book,"borrowed");
                    }
                    else {
                        attempts--;

                        System.out.println("Invalid PIN. Attempts left: " + attempts);
                    }
                }

                if (!isValid) {

                    System.out.println("Payment failed. Returning to main menu.");

                    return;
                }
            }

            else {

                System.out.println("Invalid choice. Defaulting to Cash.");

                paymentMethod = "Cash";

                System.out.println("Payment successful using Cash.");

                paymentSuccessful = true;

                Writer.borrowhistory(book, customer.getId());

                Writer.changeBookStatus(book,"borrowed");
            }

            if (paymentSuccessful) {

                Writer.borrowbook(book, customer.getId());

                System.out.println("Book borrowed successfully: " + book.getTitle());

                System.out.println("Payment Method: " + paymentMethod);



            }
        } else {
            System .out.println("Book is not available or does not exist.");
        }
    }






    @Override
    public String toString() {
        return "Borrower[ID=" + borrowerId + ", Book=" + book + "]";
    }
}
