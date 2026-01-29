import java.util.*;

public class Customer {
    private  String id;
    private  String name;
    private  String password;
    private  List<Book> cart;
    private  List<Book> borrowedBooks;
    private  List<String> orderHistory;

    public Customer(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cart = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    public Customer() {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

  /*  public void setName(String name) {
        this.name = name;
    }*/

    public String getPassword() {
        return password;
    }

  /*  public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getCart() {
        return cart;
    }

    public void setCart(List<Book> cart) {
        this.cart = cart;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<String> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<String> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }*/




    public void borrowBook(Book book) {

        borrowedBooks.add(book);
    }



  /*  public void addOrder(String orderDetails) {
        orderHistory.add(orderDetails);
    }*/

    @Override
    public String toString() {
        return "Customer ID = " + id  + ", name = " + name + ", password = " + password ;

    }
}