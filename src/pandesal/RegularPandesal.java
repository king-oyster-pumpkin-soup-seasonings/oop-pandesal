package pandesal;

public class RegularPandesal extends Pandesal {

    // CONSTRUCTOR
    public RegularPandesal(double pricePerPiece) {
        super("Regular", pricePerPiece);
    }

    // METHOD (Overridden & Polymorphic)
    @Override
    public double calculateTotalCost(int quantity) {
        return quantity * getPricePerPiece();
    }

}