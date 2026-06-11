package business;

import pandesal.Pandesal;

public class CartItem {
    private final Pandesal product;
    private int quantity;

    public CartItem(Pandesal product, int quantity) {
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

    public double getTotalCost() {
        return product.calculateTotalCost(quantity);
    }
}
