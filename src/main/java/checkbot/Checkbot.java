package checkbot;

import checkbot.parser.Parser;
import checkbot.storage.StorageFile;
import checkbot.ui.TextUi;

import java.io.IOException;

public class Checkbot {
    public static void main(String[] args) {
        TextUi.printHello();

        // Restore existing task list if applicable
        try {
            StorageFile.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Print list after restoring existing tasks
        TextUi.printTasks();

        // Parse user input after restoring task list
        Parser.parse();
    }
}
