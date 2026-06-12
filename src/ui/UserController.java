package ui;

import java.util.Scanner;

public class UserController implements ClearScreen {

    // VARIABLE
    private final Scanner scanner;

    // CONSTRUCTOR
    public UserController(Scanner scanner) {
        this.scanner = scanner;
    }

    // METHODS
    public int readInt() {
        try {
            System.out.print("(>) ");
            int choice = Integer.parseInt(scanner.nextLine());
            clearScreen();
            return choice;
        } catch (NumberFormatException e) {
            clearScreen();
            return -1;
        }
    }

    public double readDouble() {
        try {
            System.out.print("(>) ");
            double value = Double.parseDouble(scanner.nextLine());
            clearScreen();
            return value;
        } catch (NumberFormatException e) {
            clearScreen();
            return -1;
        }
    }

    public void pressEnter() {
        System.out.print("\nPress [ENTER] to continue... ");
        scanner.nextLine();
    }

    @Override
    public void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.println("\n\n\n\n");
    }
}
