package pandesal;

// CLASS (Abstracted)
public abstract class Pandesal {

    // VARIABLES (Encapsulated)
    private final String flavor;
    private final double pricePerPiece;

    // CONSTRUCTOR
    public Pandesal(String flavor, double pricePerPiece) {
        this.flavor = flavor;
        this.pricePerPiece = pricePerPiece;
    }

    // METHODS (Getters)
    public String getFlavor() {
        return flavor;
    }

    public double getPricePerPiece() {
        return pricePerPiece;
    }

    // METHOD (Abtracted)
    public abstract double calculateTotalCost(int quantity);

}
