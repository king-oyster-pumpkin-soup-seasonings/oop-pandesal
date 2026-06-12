package business;

public interface Orderable {

    double calculateTotalCost(int quantity);

    double calculateTotalCost(int quantity, double discount);

}