package Chad.Ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
//Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Chad.Ui.Main.class, args);
    }
}
