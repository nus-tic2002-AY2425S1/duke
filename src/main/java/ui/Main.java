package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

import javaro.Javaro;

/**
 * Entry point for JavaFX applications
 */
public class Main extends Application {

    private Javaro javaro = new Javaro();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Disallow user to resize window below certain limits - Set min height and width
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417); // Add this if you didn't automatically resize elements

            fxmlLoader.<MainWindow>getController().setJavaro(javaro);  // inject the Javaro instance
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}