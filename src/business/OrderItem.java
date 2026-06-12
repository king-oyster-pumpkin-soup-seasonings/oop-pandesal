package business;

import pandesal.Pandesal;

public class OrderItem {

    private final Pandesal product;
    private int quantity;

    public OrderItem(Pandesal product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Pandesal getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public double getSubtotal() {
        return product.calculateTotalCost(quantity);
    }

}
