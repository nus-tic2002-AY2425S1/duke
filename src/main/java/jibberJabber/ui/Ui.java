package jibberJabber.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public String read() {
        return scanner.nextLine().trim();
    }
    public void showWelcomeMessage() {
        Message.printWelcomeMessage("Jibber Jabber");
    }
    public void showEndingMessage() {
        Message.printEndingMessage();
    }
}
