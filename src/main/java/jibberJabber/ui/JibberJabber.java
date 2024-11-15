package jibberJabber.ui;

import jibberJabber.commands.Parser;
import jibberJabber.tasks.TaskFiles;
import jibberJabber.tasks.TaskList;

public class JibberJabber {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcomeMessage();

        String relativePath = System.getProperty("user.dir") + "/data/tasks.txt";
        TaskFiles storage = new TaskFiles(relativePath);
        TaskList taskList = storage.extractTasksFromFile();
        Parser parser = new Parser(taskList, storage);

        boolean isRunning = true;
        while (isRunning) {
            String userCommand = ui.read();
            isRunning = parser.processCommand(userCommand);
        }

        ui.showEndingMessage();
    }
}
