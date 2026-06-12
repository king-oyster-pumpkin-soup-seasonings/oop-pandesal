package ui;

import business.Order;
import business.OrderItem;
import business.Products;
import pandesal.Pandesal;

public class Display implements ClearScreen {

    // VARIABLES
    private final Products products;
    private final Order order;
    private final UserController userController;
    private int choice = 0;

    // CONSTRUCTOR
    public Display(Products products, Order order, UserController userController) {
        this.products = products;
        this.order = order;
        this.userController = userController;
    }

    // OVERRIDE METHODS
    @Override
    public void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.println("\n\n\n\n");
        showTitle();
        showStatus();
    }

    // METHODS
    public void start() {
        while (true) {
            clearScreen();
            showControls();

            choice = userController.readInt();

            if (choice == 5) {
                return;
            } else if (choice == 1)
                optionAddItem();
            else if (choice == 2)
                optionRemoveItem();
            else if (choice == 3) {
                optionCheckout();
            } else if (choice == 4) {
                optionClearOrder();
            } else {
                showInvalidChoice();
            }
        }
    }

    public void showTitle() {
        System.out.println("====================");
        System.out.println("PANDISAL PEE-OW-ES");
        System.out.println("====================");
    }

    public void showStatus() {
        System.out.println("\n:: Current Order: ");
        if (order.isEmpty()) {
            System.out.println("   (No items yet)\n");
            return;
        }

        for (int i = 0; i < order.size(); i++) {
            OrderItem item = order.getItems().get(i);
            Pandesal product = item.getProduct();
            System.out.printf("   [%d] %s Pandesal x %d = ₱%.2f%n",
                    i + 1,
                    product.getFlavor(),
                    item.getQuantity(),
                    item.getSubtotal());
        }

        System.out.printf("%n:: Total: ₱%.2f%n%n", order.getTotal());
    }

    public void showControls() {
        System.out.println(":: Controls:");
        System.out.println("   [1] Add Item");
        System.out.println("   [2] Remove Item");
        System.out.println("   [3] Checkout / Payment");
        System.out.println("   [4] Clear Order");
        System.out.println("   [5] Exit\n");
    }

    public void optionAddItem() {
        showTitle();
        System.out.println("\n:: 1 - Add Item");
        for (int i = 0; i < products.size(); i++) {
            Pandesal pandesal = products.get(i);
            System.out.printf("   [%d] %s Pandesal  \t₱ %.2f%n", i + 1, pandesal.getFlavor(),
                    pandesal.getPricePerPiece());
        }
        System.out.printf("   [%d] Back%n", products.size() + 1);
        System.out.println();

        choice = userController.readInt();
        if (choice == products.size() + 1) {
            return;
        } else if (choice > 0 && choice <= products.size()) {
            optionSelectItem(choice);
        } else {
            showInvalidChoice();
        }
    }

    public void optionSelectItem(int choice) {
        int quantity = 0;
        int selectedItem = choice - 1;
        Pandesal pandesal = products.get(selectedItem);
        
        showTitle();
        System.out.println("\n:: You have selected:\n   " + pandesal.getFlavor() + " Pandesal");
        System.out.println("\n:: Price per piece:\n   ₱" + pandesal.getPricePerPiece());
        System.out.println("\n:: Enter quantity: (0 - Cancel)");
        quantity = userController.readInt();
        if (quantity == 0) return;
        else if (quantity > 0) {
            showTitle();
            double totalCost = pandesal.calculateTotalCost(quantity);
            System.out.printf("\n:: Total cost for %d pieces of %s Pandesal:\n   ₱%.2f%n", quantity,
                    pandesal.getFlavor(), totalCost);

            System.out.println("\n:: Decision:");
            System.out.println("   [1] Add to Order");
            System.out.println("   [2] Back to Menu\n");
            choice = userController.readInt();
            if (choice == 1) {
                order.addItem(pandesal, quantity);
                showTitle();
                System.out.printf("\n%d pieces of %s pandesal %n", quantity,
                        pandesal.getFlavor());
                System.out.printf("added to order worth ₱%.2f%n", totalCost);
                userController.pressEnter();
            } else if (choice == 2) return;
            else showInvalidChoice();
        }
        else showInvalidChoice();
    }

    public void optionRemoveItem() {
        if (order.isEmpty()) {
            showTitle();
            System.out.println("\n:: No items to remove.");
            userController.pressEnter();
            return;
        }

        showTitle();
        showStatus();
        System.out.println(":: Enter item number to remove: (0 - Cancel)");
        choice = userController.readInt();

        if (choice == 0) {
            return;
        } else if (order.removeItem(choice - 1)) {
            showTitle();
            System.out.println("\n:: Item removed from order.");
            userController.pressEnter();
        } else {
            showInvalidChoice();
        }
    }

    public void optionCheckout() {
        if (order.isEmpty()) {
            showTitle();
            System.out.println("\n:: No items to checkout.");
            userController.pressEnter();
            return;
        }

        showTitle();
        showStatus();
        System.out.println(":: Enter customer payment: (0 - Cancel)");
        double payment = userController.readDouble();
        double total = order.getTotal();

        if (payment == 0) {
            return;
        } else if (payment < total) {
            showTitle();
            System.out.println("\n:: Payment is not enough.");
            userController.pressEnter();
        } else {
            showTitle();
            showStatus();
            System.out.printf(":: Payment: ₱%.2f%n", payment);
            System.out.printf(":: Change: ₱%.2f%n", payment - total);
            order.clear();
            userController.pressEnter();
        }
    }

    public void optionClearOrder() {
        if (order.isEmpty()) {
            showTitle();
            System.out.println("\n:: Order is already empty.");
            userController.pressEnter();
            return;
        }

        order.clear();
        showTitle();
        System.out.println("\n:: Current order cleared.");
        userController.pressEnter();
    }

    public void showInvalidChoice() {
        showTitle();
        System.out.print("\nInvalid choice. Please try again.\n");
        userController.pressEnter();
        clearScreen();
    }

}
