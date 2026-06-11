import java.util.Scanner;

import business.Products;
import ui.DisplayHelper;
import ui.UserController;
import ui.Display;
import user.Buyer;

public class App {
    public static void main(String[] args) {

        // OBJECTS
        Scanner scanner = new Scanner(System.in);
        DisplayHelper displayHelper = new DisplayHelper();
        UserController userController = new UserController(scanner, displayHelper);
        Products products = new Products();
        Buyer buyer = new Buyer("Customer");
        Display display = new Display(products, buyer, userController, displayHelper);

        // UI
        display.start();

        scanner.close();
    }
}
