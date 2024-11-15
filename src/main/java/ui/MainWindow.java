package ui;

import common.Constants;
import common.Messages;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
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

    public TextField getInputField() {
        return userInput;
    }

    /**
     * Prints a greeting message to the user when the application starts.
     */
    public void showWelcome() {
        dialogContainer.getChildren().add(
            DialogBox.getJavaroDialog(Messages.MESSAGE_WELCOME, javaroImage)
        );
    }

    public void delayAndExit() {
        // https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        // Create a PauseTransition to wait for 5 seconds: https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
        PauseTransition delay = new PauseTransition(Duration.seconds(Constants.FIVE));
        delay.setOnFinished(event -> {
            // Close the application after the pause
            Platform.exit();
        });
        delay.play();       // Start the delay
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        getInputField().promptTextProperty().set("Type here...");       // Set placeholder
        showWelcome();
    }

    /** Injects the Javaro instance */
    public void setJavaro(Javaro javaro) {
        this.javaro = javaro;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Javaro's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        boolean isBye = javaro.runUserInput(input);
        String commandType = javaro.getCommandType();
        System.out.println("in handleuserinput commandtype is " + commandType);

        userInput.clear();

        String javaroResponse = javaro.getUi().getJavaroResponse();

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getJavaroDialog(javaroResponse, javaroImage, commandType)
        );

        if (isBye) {
            delayAndExit();
        }

    }
}
