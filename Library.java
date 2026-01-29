import java.util.Scanner;

public class Library {

    private final UserManager userManager;

    public Library() {

        this.userManager = new UserManager();
    }

    public UserManager getUserManager() {

        return userManager;
    }






    public static void adminMenu(Library l, Scanner s, String username, String password) {

        final String ADMIN_USERNAME = "admin";
        final String ADMIN_PASSWORD = "1234";

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {

            System.out.println("Login successful. Welcome, Admin!");

            boolean isAdminRunning = true;

            while (isAdminRunning) {
                System.out.println("\n    Admin Menu   ");

                System.out.println("1. Add New Book");

                System.out.println("2. View All Books");

                System.out.println("3. Remove Book");

                System.out.println("4. View Borrowed Books");

                System.out.println("5. View Borrow History");

                System.out.println("6. View All Customers");

                System.out.println("7. View Bought Books");

                System.out.println("8. Back to Main Menu");

                System.out.print("Enter choice: ");

                int choice = s.nextInt();
                s.nextLine();

                switch (choice) {
                    case 1 -> BookManager.addBook(l, s);
                    case 2 -> BookManager.viewAllBooks(l);
                    case 3 -> BookManager.removeBook(l, s);
                    case 4 -> BookManager.viewborrowedbooks(l);
                    case 5 -> BookManager.viewborrowhistory(l,s);
                    case 6 -> UserManager.viewcustomers(l);
                    case 7 -> BookManager.viewbuyhistory(l);
                    case 8 -> isAdminRunning = false;
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials! Returning to main menu.");
        }
    }









    public static void userLoggedInMenu(Library library, Customer customer, Scanner scanner) {
        boolean isUserLoggedIn = true;

        while (isUserLoggedIn) {

            System.out.println("\n   User Menu    ");

            System.out.println("1. View Available Books");

            System.out.println("2. Borrow a Book");

            System.out.println("3. Return a Book");

            System.out.println("4. Buy a Book");

            System.out.println("5. Logout");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1 -> BookManager.viewavailablebooks(library);

                case 2 -> Borrower.borrowBook(library, customer, scanner);

                case 3 -> BorrowerHistory.returnBook(library,customer,scanner);

                case 4 ->Order.buyBook(library, customer, scanner);

                case 5 -> isUserLoggedIn = false;

                default -> System.out.println("Invalid choice! Please try again.");

            }
        }
    }




}
