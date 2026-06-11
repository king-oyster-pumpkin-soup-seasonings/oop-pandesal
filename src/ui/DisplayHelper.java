package ui;

public class DisplayHelper {
    public void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.println("\n\n\n\n");
    }
}
