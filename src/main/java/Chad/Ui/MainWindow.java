package Chad.Ui;

import Chad.Chad;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration; 
/**
 * Controller for the main GUI.
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

    private Chad chad;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image chadImage = new Image(this.getClass().getResourceAsStream("/images/DaChad.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();

    }

    /** Injects the Chad instance */
    public void setChad(Chad c) {
        chad = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chad's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chad.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChadDialog(response, chadImage)
        );
        
        boolean shallExit = chad.shallExit();
        if(shallExit){
            
            // Create Timeline to wait 5 seconds before exiting
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                Platform.exit(); // This will close the application
            }));
            timeline.setCycleCount(1); // Run once
            timeline.play(); // Start the countdown
        }

        userInput.clear();
    }

        /**
     * Show welcome message in the dialog container.
     */
    private void showWelcome() {
        String welcomeMessage = "Hello from Chad!\n What can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getChadDialog(welcomeMessage, chadImage));
    }


}
