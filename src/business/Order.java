package business;

import java.util.ArrayList;
import java.util.List;
import pandesal.Pandesal;

public class Order {

    private final List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(Pandesal product, int quantity) {
        OrderItem existingItem = findItem(product);

        if (existingItem == null) {
            items.add(new OrderItem(product, quantity));
        }
        else existingItem.addQuantity(quantity);
        
    }

    public boolean removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    public void clear() { items.clear(); }

    public boolean isEmpty() { return items.isEmpty(); }

    public int size() { return items.size(); }

    // METHOD: Getters
    public OrderItem get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }
    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    private OrderItem findItem(Pandesal product) {
        for (OrderItem item : items) {
            if (item.getProduct() == product) {
                return item;
            }
        }
        return null;
    }

}
