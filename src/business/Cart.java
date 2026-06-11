package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pandesal.Pandesal;

public class Cart {
    private final ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(Pandesal product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                item.addQuantity(quantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (CartItem item : items) {
            totalCost += item.getTotalCost();
        }
        return totalCost;
    }

    public void clear() {
        items.clear();
    }
}
