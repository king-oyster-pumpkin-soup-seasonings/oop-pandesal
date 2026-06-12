package pandesal;

import business.Orderable;

public class SpecialPandesal extends Pandesal implements Orderable {

    // VARIABLES (Encapsulated)
    private final double flavorCost;

    // CONSTRUCTOR
    public SpecialPandesal(String flavor, double pricePerPiece, double flavorCost) {
        super(flavor, (pricePerPiece + flavorCost));
        this.flavorCost = flavorCost;
    }

    // METHOD (Overridden & Polymorphic)
    @Override
    public double calculateTotalCost(int quantity) {
        return quantity * getPricePerPiece();
    }

    @Override
    public double calculateTotalCost(int quantity, double discount) {
        double discountedPrice = getPricePerPiece() * (1.0 - discount);
        return quantity * Math.max(0, discountedPrice);
}

    public double getFlavorCost() {
        return flavorCost;
    }

}
