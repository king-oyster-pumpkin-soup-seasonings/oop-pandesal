package ui;

import java.util.List;
import business.CartItem;
import business.Products;
import pandesal.Pandesal;
import user.Buyer;

public class Display {
    private final Products products;
    private final Buyer buyer;
    private final UserController userController;
    private final DisplayHelper displayHelper;

    public Display(Products products, Buyer buyer, UserController userController, DisplayHelper displayHelper) {
        this.products = products;
        this.buyer = buyer;
        this.userController = userController;
        this.displayHelper = displayHelper;
    }

    public void start() {
        while (true) {
            clearAndShowTitle();
            showControls();

            int choice = userController.readInt();

            if (choice == 5) {
                System.out.println("\nThank you for visiting Ricky's Pandesal!");
                return;
            } else if (choice == 1 || choice == 2) {
                showMenu();
                int productChoice = userController.readInt();
                if (productChoice == products.size() + 1) {
                    continue;
                } else if (productChoice > 0 && productChoice <= products.size()) {
                    showSelectedProductDetails(productChoice);
                } else {
                    showInvalidChoice();
                }
            } else if (choice == 3) {
                showCart();
            } else if (choice == 4) {
                checkout();
            } else {
                showInvalidChoice();
            }
        }
    }

    private void clearAndShowTitle() {
        displayHelper.clearScreen();
        showTitle();
    }

    public void showTitle() {
        System.out.println("==================");
        System.out.println("Ricky's Pandesal");
        System.out.println("==================");
    }

    public void showControls() {
        System.out.println("[1] View Menu");
        System.out.println("[2] Add to Cart");
        System.out.println("[3] View Cart");
        System.out.println("[4] Checkout");
        System.out.println("[5] Exit");
    }

    public void showMenu() {
        List<Pandesal> productList = products.getProductList();
        System.out.println("Menu:");
        for (int i = 0; i < productList.size(); i++) {
            Pandesal pandesal = productList.get(i);
            System.out.printf("[%d] %s - ₱%.2f%n", i + 1, pandesal.getFlavor(), pandesal.getPricePerPiece());
        }
        System.out.printf("[%d] Back%n", productList.size() + 1);
        System.out.println();
    }

    public void showSelectedProductDetails(int choice) {
        int selectedProduct = choice - 1;
        Pandesal product = products.getProduct(selectedProduct);
        int quantity = 0;
        System.out.println("You have selected:\n> " + product.getFlavor());
        System.out.println("\nPrice per piece:\n> ₱" + product.getPricePerPiece());
        System.out.println("\nEnter quantity:");
        quantity = userController.readInt();
        if (quantity < 1) {
            showInvalidChoice();
            return;
        }
        double totalCost = product.calculateTotalCost(quantity);
        System.out.printf("Total cost for %d pieces of %s:\n> ₱%.2f%n", quantity,
                product.getFlavor(), totalCost);
        System.out.println("\nDecision:");
        System.out.println("[1] Add to Cart");
        System.out.println("[2] Back to Menu");
        choice = userController.readInt();
        if (choice == 1) {
            buyer.addToCart(product, quantity);
            System.out.printf("\n%d pieces of %s pandesal added to cart.%n", quantity,
                    product.getFlavor());
            userController.pressEnterToContinue();
        } else if (choice == 2) {
            return;
        } else {
            showInvalidChoice();
        }
    }

    public void showCart() {
        if (buyer.getCart().isEmpty()) {
            System.out.println("Your cart is empty.");
            userController.pressEnterToContinue();
            return;
        }

        printCart();
        System.out.printf("Total: ₱%.2f%n", buyer.getCart().getTotalCost());
        userController.pressEnterToContinue();
    }

    public void checkout() {
        if (buyer.getCart().isEmpty()) {
            System.out.println("Your cart is empty.");
            userController.pressEnterToContinue();
            return;
        }

        printCart();
        System.out.printf("Total: ₱%.2f%n", buyer.getCart().getTotalCost());
        buyer.getCart().clear();
        System.out.printf("Thank you, %s! Your order has been checked out.%n", buyer.getName());
        userController.pressEnterToContinue();
    }

    private void printCart() {
        System.out.println("Cart:");
        for (CartItem item : buyer.getCart().getItems()) {
            Pandesal product = item.getProduct();
            System.out.printf("%d x %s - ₱%.2f%n",
                    item.getQuantity(),
                    product.getFlavor(),
                    item.getTotalCost());
        }
    }

    public void showInvalidChoice() {
        System.out.println("\nInvalid choice. Please try again.");
        userController.pressEnterToContinue();
    }

}
