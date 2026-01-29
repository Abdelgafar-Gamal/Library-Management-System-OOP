import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends Customer {
    private final List<Order> orders; // Stores the list of orders

   public OrderHistory(String id, String name, String password) {
        super(id, name, password);
        this.orders = new ArrayList<>();
    }

    // بيضيف الاوردرات الجديده
   /* public void addOrder(Order order) {
        orders.add(order);
        super.addOrder(order.toString());
    }


    public List<Order> getOrders() {
        return orders;
    }*/

    @Override
    public String toString() {
        return "OrderHistory[Customer=" + getName() + ", Orders=" + orders + "]";
    }
}
