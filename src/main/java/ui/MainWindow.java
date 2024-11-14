package ui;

import common.Messages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javaro.Javaro;

/**
 * Controller for the Javaro GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    // Image location (e.g., /images/DaUser.png) is given relative to the main/resources folder and there is a / in front
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image javaroImage = new Image(this.getClass().getResourceAsStream("/images/javaro.jpg"));

    private Javaro javaro;

    public void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getJavaroDialog(Messages.MESSAGE_WELCOME, javaroImage));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /** Injects the Javaro instance */
    public void setJavaro(Javaro javaro) {
        this.javaro = javaro;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = javaro.getResponse(input);
        boolean isBye = javaro.runUserInput(input);

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getJavaroDialog(response, javaroImage)
        );

        userInput.clear();
    }
}
