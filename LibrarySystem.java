import java.util.*;

public class LibrarySystem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Library library = new Library();



        boolean isRunning = true;

        final String ADMIN_USERNAME = "admin";
        final String ADMIN_PASSWORD = "1234";


        while (isRunning) {
            System.out.println("\n    Library System    ");

            System.out.println("1. Register");

            System.out.println("2. Login");

            System.out.println("3. Exit");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    UserManager.addcustomer(scanner);
                }
                case 2 -> {System.out.print("Enter Username: ");

                    String username = scanner.nextLine();

                    System.out.print("Enter Password: ");

                    String password = scanner.nextLine();

                    if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD))
                    {

                        Library.adminMenu(library, scanner,username,password);
                    }

                    else
                        passwordcheck(library, scanner,username,password);}
                case 3 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }










    private static void passwordcheck(Library l, Scanner s,String username,String password) {

        Reader.showallcustomers();
        Customer customer = l.getUserManager().findCustomerById(username);

        if (customer != null && customer.getId().equals(username) && customer.getPassword().equals(password)) {

            System.out.println("Login successful!");
            Library.userLoggedInMenu(l, customer, s);
        }
        else {

            System.out.println("Invalid credentials! Returning to main menu.");
        }

    }





}
