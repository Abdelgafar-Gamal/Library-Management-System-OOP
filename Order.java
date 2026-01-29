import java.util.Scanner;

public class Order {
    private String orderId;
    private Book book;
    private int quantity;
    private double totalPrice;

  /*  public Order(String orderId, Book book, int quantity) {
        this.orderId = orderId;
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = book.getPrice() * quantity;
    }



    public String getOrderId() {
        return orderId;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }*/











    public static void buyBook(Library library, Customer customer, Scanner scanner) {

        System.out.println("    Buy a Book   ");

        System.out.print("Enter Book ID to Buy: ");

        String bookId = scanner.nextLine();

        Book book = Reader.searchforbookid(bookId);

        if (book != null && book.getStatus().equalsIgnoreCase("available")) {

            System.out.println("Choose payment method:");

            System.out.println("1. Cash");

            System.out.println("2. Visa");

            System.out.print("Enter choice: ");

            int paymentChoice = scanner.nextInt();

            scanner.nextLine();

            String paymentMethod;
            if (paymentChoice == 1) {

                paymentMethod = "Cash";

                System.out.println("Payment successful using Cash.");

                Writer.removeBook(bookId);

                Writer.boughthistory(book, customer.getId());


            } else if (paymentChoice == 2) {
                paymentMethod = "Visa";

                boolean isValid = false;

                int attempts = 3; // المحاولات بتاعه الرقم السري


                while (attempts > 0 && !isValid) {

                    System.out.print("Enter Visa PIN (4 digits): ");

                    String pin = scanner.nextLine();

                    //لازم ندخل اربع ارقام عشان يظبط
                    if (pin.matches("\\d{4}")) {

                        isValid = true;
                        System.out.println("Payment successful using Visa.");

                        Writer.removeBook(bookId);
                        Writer.boughthistory(book, customer.getId());

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

                Writer.removeBook(bookId);
                Writer.boughthistory(book, customer.getId());
            }


            System.out.println("Book purchased successfully: " + book.getTitle());
            System.out.println("Payment Method: " + paymentMethod);
            System.out.print("Would you like to rate this book? (yes/no): ");

            String rateConfirm = scanner.nextLine();
            if (rateConfirm.equalsIgnoreCase("yes")) {
                System.out.print("Enter your rating (1-5): ");

                int userRating = scanner.nextInt();
                scanner.nextLine();
                if (userRating >= 1 && userRating <= 5) {

                    System.out.print("Enter your review: ");

                    String review = scanner.nextLine();
                    book.addReview(review, userRating);
                    System.out.println("Thank you for your feedback!");
                }
                else {
                    System.out.println("Invalid rating. Skipping...");
                }
            }
        }
        else {
            System.out.println("Book is not available or does not exist.");
        }
    }












    @Override
    public String toString() {
        return "Order[ID=" + orderId + ", Book=" + book.getTitle() + ", Quantity=" + quantity +
                ", Total Price=" + totalPrice + "]";
    }
}