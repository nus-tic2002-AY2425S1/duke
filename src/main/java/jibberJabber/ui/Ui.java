package jibberJabber.ui;

import java.util.Scanner;
/**
 * The Ui class handles all user interactions within the Jibber Jabber application while running
 * Upon load, it displays the welcome message and after, reads the user input upon each interaction
 * It displays the ending message when the system ends or the user input the bye keyword command
 */
public class Ui {
    private final Scanner scanner;
    /**
     * Constructs a Ui object of the Ui class.
     * Sets up the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Reads a line of input from the user, trimmed of any leading and trailing whitespace.
     *
     * @return The trimmed user input as a String.
     */
    public String read() {
        return scanner.nextLine().trim();
    }
    /**
     * This method returns the welcome message to the user.
     */
    public void showWelcomeMessage() {
        Message.printWelcomeMessage("Jibber Jabber");
    }
    /**
     * This method returns the ending message to the user.
     */
    public void showEndingMessage() {
        Message.printEndingMessage();
    }
}
