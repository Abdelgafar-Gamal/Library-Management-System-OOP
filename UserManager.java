import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserManager {
    public static List<Customer> customer = new ArrayList<>();
    //private static final String customerfile = "customer.txt";


    public static void addcustomer( Scanner s) {

        System.out.print("Enter your username: ");
        String customerid = s.nextLine();


        System.out.print("Enter your name: ");
        String customername = s.nextLine();


        System.out.print("Enter your password: ");
        String customerpassword = s.nextLine();


        Customer customer = new Customer(customerid,customername,customerpassword);
        Writer.addnewcustomer(customer);

    }


    public Customer findCustomerById(String id) {

        List<Customer> customers = Reader.showallcustomers();
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public static void viewcustomers(Library l ) {
        List<Customer> customers = Reader.showallcustomers();


        if (customers.isEmpty()) {
            System.out.println("No Customers found.");
        }
        else {
            System.out.println("All Customers:");

            for (Customer customer : customers) {
                System.out.println(customers);
            }

        }
    }


    public static void add_rating_and_review(Book b,Scanner s){

        System.out.print("Would you like to rate this book? (yes/no): ");
        String rateChoice = s.nextLine();


        if (rateChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter your rating (1-5): ");

            double rating = s.nextInt();

            s.nextLine();

            System.out.print("Enter your review: ");
            String review = s.nextLine();

            Writer.saveRatingAndReview(b,rating,review);
            System.out.println("Thank you for your feedback!");
        }
        else {
            System.out.println("Thank You!");
        }




    }






}