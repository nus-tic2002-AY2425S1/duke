package ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
// Solution below referenced from https://se-education.org/guides/tutorials/javaFxPart1.html
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
