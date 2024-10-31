package wkduke.ui;

import wkduke.common.Messages;
import wkduke.exception.WKDukeException;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles user interface for the WKDuke application.
 * Provides methods to display messages, errors, and read user commands.
 */
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

    /**
     * Constructs a {@code Ui} object for interacting with the user.
     * Initializes the input scanner and output stream.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Prints a series of messages to the user, surrounded by a border line.
     *
     * @param messages The messages to print.
     */
    public void printMessages(String... messages) {
        showLine();
        for (String message : messages) {
            out.println(INDENT + message);
        }
        showLine();
    }

    /**
     * Reads a command from the user, skipping empty lines.
     *
     * @return The command entered by the user as a {@code String}.
     */
    public String readCommand() {
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    /**
     * Displays an error message based on the provided {@code WKDukeException}.
     * Includes error class, message, additional details, and help information if available.
     *
     * @param e The exception containing error information.
     */
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

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        printMessages(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Displays an initialization error message based on the provided {@code WKDukeException}.
     * Includes error class, message, additional details, and help information if available.
     *
     * @param e The exception containing error information.
     */
    public void showInitError(WKDukeException e) {
        List<String> messages = new ArrayList<>();
        messages.add(String.format("[Init-Error]-[%s]", e.getClass().getSimpleName()));
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

    /**
     * Displays a line border to separate sections in the console output.
     */
    public void showLine() {
        out.println(BORDER_LINE);
    }

    /**
     * Displays the welcome logo and message to the user.
     */
    public void showWelcome() {
        printMessages(WK_DUKE_LOGO, Messages.MESSAGE_WELCOME);
    }
}
