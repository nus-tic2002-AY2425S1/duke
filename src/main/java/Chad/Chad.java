package Chad;

import Chad.Command.Command;
import Chad.Exception.ChadException;
import Chad.Parser.Parser;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.TextUi;

/**
 * The main class that handles the execution of the task management application.
 * Initializes the user interface, task list, and storage management.
 */
public class Chad {
    // Storage object to manage file interactions
    Storage storage;

    // List of tasks managed by the application
    TaskList tasks;

    // User interface for input and output
    TextUi ui;

    // Indicates the exit status of the application
    boolean isExit;

    // File path for data storage
    static String filePath = "./data/chad.txt";

    /**
     * Constructs a new Chad instance with the specified file path for storage.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Chad(String filePath) {
        ui = new TextUi(); // Initialize the user interface
        tasks = new TaskList(); // Initialize the task list regardless of storage success
        initializeStorage(filePath); // Handle retrieval of stored data
    }

    /**
     * Initializes the storage for the application.
     *
     * @param filePath The path to the file used for storage.
     */
    void initializeStorage(String filePath) {
        try {
            storage = new Storage(filePath); // Attempt to set up storage with provided file path
        } catch (ChadException e) {
            ui.showError("Failed to initialize storage: " + e.getMessage());
            // Optionally provide fallback behavior
            // Tasks can be managed in memory without loading from file
        }
    }

    /**
     * Runs the main application loop, displaying the welcome message
     * and executing user commands until termination.
     */
    public void run() {
        ui.showWelcome(); // Show the welcome message to the user
        isExit = false; // Set exit condition to false to enter the loop
        while (!isExit) {
            String fullCommand = ui.readCommand();
            isExit = executeUserCommand(fullCommand); // Main change
        }
    }


    /**
     * Executes commands entered by the user.
     */
    boolean executeUserCommand(String fullCommand) {
        try {
            // Read the command from the user
            ui.showLine(); // Show the divider line
            Command command = Parser.parse(fullCommand); // Parse the command string into a Command object
            command.execute(tasks, ui, storage); // Execute the command with the current task list, UI, and storage
            return command.isExit();
            //isExit = command.isExit(); // Update the exit condition based on the command's result
        } catch (ChadException e) {
            ui.showError(e.getMessage()); // Display error message to the user
            return false;
        } finally {
            ui.showLine(); // Show the divider line regardless of outcome
        }
    }

    /**
     * Stops the application, showing a goodbye message.
     */
    public void stop() {
        ui.showBye(); // Notify the user that the application is stopping
    }

    /**
     * The application entry point.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Chad(filePath).run(); // Create a new instance of Chad and start the application
    }
}
