package ui;

import java.util.Scanner;

public class UserController {
    private final Scanner scanner;
    private final DisplayHelper displayHelper;

    public UserController(Scanner scanner, DisplayHelper displayHelper) {
        this.scanner = scanner;
        this.displayHelper = displayHelper;
    }

    public int readInt() {
        try {
            System.out.print("> ");
            int choice = Integer.parseInt(scanner.nextLine());
            displayHelper.clearScreen();
            return choice;
        } catch (NumberFormatException e) {
            displayHelper.clearScreen();
            return 0;
        }
    }

    public void pressEnterToContinue() {
        System.out.print("\nPress [ENTER] to continue... ");
        scanner.nextLine();
    }
}
