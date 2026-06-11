package pandesal;

public class SpecialPandesal extends Pandesal {

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

    public double getFlavorCost() {
        return flavorCost;
    }

}
