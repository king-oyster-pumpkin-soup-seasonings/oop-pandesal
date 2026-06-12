package app;

import java.util.Scanner;

import business.Order;
import business.Products;
import ui.UserController;
import ui.Display;

public class App {
    public static void main(String[] args) {

        // OBJECTS
        Products products = new Products();
        Order order = new Order();
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController(scanner);
        Display display = new Display(products, order, userController);

        // UI
        display.start();

        scanner.close();
    }
}
