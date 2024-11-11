package commands;

import common.Constants;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to show all deadlines and events that are scheduled on a specific date.
 * <p>
 * The ShowCommand class retrieves and displays tasks from the task list that are scheduled for a given date.
 * It provides feedback to the user about the tasks scheduled on that date.
 */
public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";
    public static final String DEADLINES_EVENTS_ON = "deadlines / events on";
    public static final String MESSAGE_EMPTY_LIST = "You have no" + SPACE + DEADLINES_EVENTS_ON + SPACE;
    public static final String MESSAGE_SHOW_SUCCESS_PRE = "Here are the" + SPACE + DEADLINES_EVENTS_ON + SPACE;

    protected final LocalDate date;

    /**
     * Constructs a ShowCommand with the specified date.
     *
     * @param date represents the date for which to show deadlines and events.
     */
    public ShowCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the date associated with the command.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Executes the command to display tasks that are scheduled on a specific date.
     *
     * @param taskList represents the list of tasks to check for the specified date.
     * @param ui       represents the user interface to interact with the user.
     * @param storage  represents the storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        LocalDate inputDate = getDate();

        // Retrieve all tasks scheduled on the specified date
        List<Task> tasksOnDate = taskList.getAllTasksOnDate(inputDate);

        // Check if there are no tasks on the specified date
        if (tasksOnDate.isEmpty()) {
            String[] messages = {MESSAGE_EMPTY_LIST + inputDate};
            ui.printMessage(messages);
            return;
        }

        // Prepare the message to display to the user
        ArrayList<String> messages =
            new ArrayList<>(List.of(String.format("%s%s:", MESSAGE_SHOW_SUCCESS_PRE, inputDate)));

        int taskListSize = taskList.getSize();

        // Iterate through the task list and add tasks scheduled on the specified date
        for (int i = 0; i < taskListSize; i++) {
            Task current = taskList.getTask(i);         // taskList.get(i) contains the checkbox
            if (current.isOnDate(inputDate)) {
                String index = Integer.toString(i + 1);
                String line = index + Constants.DOT_SPACE + current;
                messages.add(line);
            }
        }

        // Print the message list containing tasks scheduled on the specified date
        ui.printMessage(messages);
    }
}
