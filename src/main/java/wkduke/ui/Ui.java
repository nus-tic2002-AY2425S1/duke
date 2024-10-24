package wkduke.ui;

import wkduke.common.Messages;
import wkduke.exception.WKDukeException;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String BORDER_LINE = "\t_________________________________________________________________________";
    private static final String INDENT = "\t ";
    private static final String WK_DUKE_LOGO = """
             ___       __   ___  __    ________  ___  ___  ___  __    _______
            \t |\\  \\     |\\  \\|\\  \\|\\  \\ |\\   ___ \\|\\  \\|\\  \\|\\  \\|\\  \\ |\\  ___ \\
            \t \\ \\  \\    \\ \\  \\ \\  \\/  /|\\ \\  \\_|\\ \\ \\  \\\\\\  \\ \\  \\/  /|\\ \\   __/|
            \t  \\ \\  \\  __\\ \\  \\ \\   ___  \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\   ___  \\ \\  \\_|/__
            \t   \\ \\  \\|\\__\\_\\  \\ \\  \\\\ \\  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\
            \t    \\ \\____________\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\
            \t     \\|____________|\\|__| \\|__|\\|_______|\\|_______|\\|__| \\|__|\\|_______|
            """;

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void printMessages(String... messages) {
        showLine();
        for (String message : messages) {
            out.println(INDENT + message);
        }
        showLine();
    }

    public String readCommand() {
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    public void showError(WKDukeException e) {
        List<String> messages = new ArrayList<>();
        messages.add(String.format("[Error]-[%s]", e.getClass().getSimpleName()));
        if (e.getMessage() != null && !e.getMessage().trim().isEmpty()) {
            messages.add(String.format(" Message: %s", e.getMessage()));
        }
        if (e.getDetail() != null && !e.getDetail().trim().isEmpty()) {
            messages.add(String.format(" Info: %s", e.getDetail()));
        }
        if (e.getHelp() != null && !e.getHelp().trim().isEmpty()) {
            messages.add(String.format(" Help: %s", e.getHelp()));
        }
        printMessages(messages.toArray(new String[0]));
    }

    public void showGoodbyeMessage() {
        printMessages(Messages.MESSAGE_GOODBYE);
    }

    public void showInitError(WKDukeException e) {
        List<String> messages = new ArrayList<>();
        messages.add(String.format("[Init-Error]-[%s]", e.getClass().getSimpleName()));
        if (e.getMessage() != null && !e.getMessage().trim().isEmpty()) {
            messages.add(String.format(" Message: %s", e.getMessage()));
        }
        if (e.getDetail() != null && !e.getDetail().trim().isEmpty()) {
            messages.add(String.format(" Info: %s", e.getDetail()));
        }
        printMessages(messages.toArray(new String[0]));
    }

    public void showLine() {
        out.println(BORDER_LINE);
    }

    public void showWelcome() {
        printMessages(WK_DUKE_LOGO, Messages.MESSAGE_WELCOME);
    }
}
