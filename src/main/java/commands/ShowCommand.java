package commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to show all deadlines and events that are scheduled on a specific date.
 *
 * The ShowCommand class retrieves and displays tasks from the task list that are scheduled for a given date.
 * It provides feedback to the user about the tasks scheduled on that date.
 */
public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + 
        OPEN_ANGLE_BRACKET + Constants.DATE + CLOSE_ANGLE_BRACKET;
    public static final String DEADLINES_EVENTS_ON = "deadlines / events on";
    public static final String MESSAGE_EMPTY_LIST = "You have no" + SPACE + DEADLINES_EVENTS_ON + SPACE;

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

        LocalDate date = getDate();
        final String MESSAGE_SHOW_SUCCESS_PRE = "Here are the" + SPACE + DEADLINES_EVENTS_ON + SPACE + date + Constants.COLON;

        // Retrieve all tasks scheduled on the specified date
        // Sort the tasks by their LocalDateTime
        // https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
        TaskList scheduledTasks = taskList.getScheduledTasks(date);

        // System.out.println("after sort " + tasksOnDate);

        // Check if there are no tasks on the specified date
        ui.printEmptyListMessage(scheduledTasks, MESSAGE_EMPTY_LIST);

        // Prepare the message to display to the user
        // Iterate through the task list and add tasks scheduled on the specified date
        ArrayList<String> messages = ui.getTaskMessages(MESSAGE_SHOW_SUCCESS_PRE, scheduledTasks);

        // Print the message list containing tasks scheduled on the specified date
        ui.printMessage(messages);
    }
}
