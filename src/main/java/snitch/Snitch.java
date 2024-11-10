package snitch;

import snitch.command.Command;
import snitch.task.TaskList;
import snitch.Storage;
import snitch.Ui;
import snitch.SnitchException;
import snitch.Parser;

/**
 * The main class for the Snitch chatbot.
 * Handles initialization, running the chatbot, and processing user commands.
 */
public class Snitch {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Snitch chatbot.
     * Initializes the user interface, storage, and task list.
     */
    public Snitch(String filePath) {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            System.out.println("Previous Tasks loaded successfully.");
        } catch (SnitchException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
            tasks = new TaskList(); // Default to empty task list if loading fails
        }
    }

    /**
     * Runs the Snitch chatbot.
     * Continuously reads and processes user input until the "bye" command is received.
     */
    public void run() {
        while (true) {
            try {
                String userInput = ui.readCommand(); // Get user input

                if (userInput.equalsIgnoreCase("bye")) {
                    ui.showGoodbye();
                    break; // Exit the loop
                }

                if (userInput.equalsIgnoreCase("list")) {
                    if (tasks.isEmpty()) {
                        throw new SnitchException("Meow. Your task list is empty.");
                    }
                    ui.showTasks(tasks);
                } else {
                    Command command = Parser.parse(userInput);
                    command.execute(tasks, ui, storage);
                }

            } catch (SnitchException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the Snitch chatbot.
     */
    public static void main(String[] args) {
        new Snitch("./data/snitch.txt").run(); // Start the app
    }
}