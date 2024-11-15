package wkduke.ui;

import wkduke.common.Messages;
import wkduke.exception.WKDukeException;
import wkduke.task.Task;
import wkduke.task.TaskList;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles user interface for the WKDuke application.
 * Provides methods to display messages, errors, and read user commands.
 */
public class Ui {
    private static final String BORDER_LINE = "\t_________________________________________________________________________";
    private static final String INDENT = "\t ";
    private static final String WK_DUKE_LOGO = """
             ___       __   ___  __    ________  ___  ___  ___  __    _______
            \t |\\  \\     |\\  \\|\\  \\|\\  \\ |\\   ___ \\|\\  \\|\\  \\|\\  \\|\\  \\ |\\  ___ \\
            \t \\ \\  \\    \\ \\  \\ \\  \\/  /|\\ \\  \\_|\\ \\ \\  \\\\\\  \\ \\  \\/  /|\\ \\   __/|
            \t  \\ \\  \\  __\\ \\  \\ \\   ___  \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\   ___  \\ \\  \\_|/__
            \t   \\ \\  \\|\\__\\_\\  \\ \\  \\\\ \\  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\
            \t    \\ \\____________\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\
            \t     \\|____________|\\|__| \\|__|\\|_______|\\|_______|\\|__| \\|__|\\|_______|
            """;

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a {@code Ui} object for interacting with the user.
     * Initializes the input scanner and output stream.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Adds a formatted message to the list if the content is not null or empty.
     *
     * @param messages The list to add the message to.
     * @param label    The label for the message (e.g., "Message", "Info").
     * @param content  The content of the message.
     */
    private void addMessageIfNotEmpty(List<String> messages, String label, String content) {
        if (content != null && !content.trim().isEmpty()) {
            messages.add(String.format(" %s: %s", label, content));
        }
    }

    /**
     * Formats tasks with their index for display.
     *
     * @param taskList The taskList used to determine task index.
     * @param tasks    The tasks to format.
     * @return A list of formatted task strings.
     * @throws AssertionError If the taskList is empty and tasks are present.
     */
    private List<String> formatTasksWithIndex(TaskList taskList, List<Task> tasks) {
        // The 'tasks' list is expected to come from 'taskList', as there is a dependency to retrieve task's index
        // based on their position in 'taskList'.
        if (!tasks.isEmpty()) {
            assert !taskList.isEmpty() : "Precondition failed: 'taskList' cannot be empty when 'tasks' list is populated";
        }

        // Determine the number of digits needed for formatting
        int totalTasks = taskList.size();
        int digits = String.valueOf(totalTasks).length(); // Get the number of digits in the total size

        List<String> formattedTasks = new ArrayList<>();
        for (Task task : tasks) {
            int taskIndex = taskList.getTaskIndex(task);
            String index = (taskIndex == -1) ? "X" : String.format("%" + digits + "d", taskIndex + 1); // Align the index
            formattedTasks.add(String.format(" %s. %s", index, task));
        }
        return formattedTasks;
    }

    /**
     * Displays an exception message based on the provided {@code WKDukeException}.
     * Includes error class, message, additional details, and help information if available.
     *
     * @param prefix The prefix to display with the exception (e.g., "Error" or "Init-Error").
     * @param e      The exception containing error information.
     */
    private void showException(String prefix, WKDukeException e) {
        List<String> messages = new ArrayList<>();
        messages.add(String.format("[%s]-[%s]", prefix, e.getClass().getSimpleName()));
        addMessageIfNotEmpty(messages, "Message", e.getMessage());
        addMessageIfNotEmpty(messages, "Detail", e.getDetail());
        addMessageIfNotEmpty(messages, "Help", e.getHelp());
        printMessages(messages.toArray(new String[0]));
    }

    /**
     * Displays a line border to separate sections in the console output.
     */
    private void showLine() {
        out.println(BORDER_LINE);
    }

    /**
     * Prints a series of messages to the user, surrounded by a borderline.
     *
     * @param messages The messages to print.
     */
    public void printMessages(String... messages) {
        showLine();
        for (String message : messages) {
            out.println(INDENT + message);
        }
        showLine();
    }

    /**
     * Prints a single UI task group with its header, footer, and tasks.
     *
     * <p>This method wraps the specified {@link UiTaskGroup} into a list and delegates the printing
     * to {@link #printUiTaskGroups(TaskList, List)}.</p>
     *
     * @param taskList    The task list used to determine task indices.
     * @param uiTaskGroup The {@link UiTaskGroup} to print.
     * @throws AssertionError If the task list is empty while tasks are present in the group.
     */
    public void printUiTaskGroup(TaskList taskList, UiTaskGroup uiTaskGroup) {
        printUiTaskGroups(taskList, List.of(uiTaskGroup));
    }

    /**
     * Prints task groups with headers and footers, where each task is prefixed by its index in the TaskList.
     *
     * <p>Each {@link UiTaskGroup} in the list contains a header, a footer, and a list of tasks. The tasks are
     * formatted with their 1-based index as determined by their position in the provided {@link TaskList}.
     * UI task groups with no tasks are skipped.</p>
     *
     * @param taskList   The task list used to determine the index of each task.
     * @param taskGroups A list of {@link UiTaskGroup} objects, each containing a header, footer, and list of tasks to print.
     */
    public void printUiTaskGroups(TaskList taskList, List<UiTaskGroup> taskGroups) {
        List<String> messages = new ArrayList<>();
        for (UiTaskGroup uiTaskGroup : taskGroups) {
            List<Task> tasks = uiTaskGroup.tasks();
            if (tasks.isEmpty()) {
                continue;
            }
            messages.add(uiTaskGroup.header());
            messages.addAll(formatTasksWithIndex(taskList, tasks));
            messages.add(uiTaskGroup.footer());
        }
        printMessages(messages.toArray(new String[0]));
    }

    /**
     * Reads a command from the user, skipping empty lines.
     *
     * @return The command entered by the user as a {@code String}.
     */
    public String readCommand() {
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    /**
     * Displays a general error message based on the provided {@code WKDukeException}.
     *
     * @param e The exception containing error information.
     */
    public void showError(WKDukeException e) {
        showException("Error", e);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        printMessages(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Displays an initialization error message based on the provided {@code WKDukeException}.
     *
     * @param e The exception containing error information.
     */
    public void showInitError(WKDukeException e) {
        showException("Init-Error", e);
    }

    /**
     * Displays the welcome logo and message to the user.
     */
    public void showWelcome() {
        printMessages(WK_DUKE_LOGO, Messages.MESSAGE_WELCOME);
    }
}
