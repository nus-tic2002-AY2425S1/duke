package commands;

import java.time.LocalDate;

import common.Constants;
import common.Messages;
import storage.Storage;
import task.TaskList;
import ui.Ui;

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

    protected final LocalDate date;

    /**
     * Constructs a ShowCommand with the specified date.
     * 
     * @param date represents the date for which to show deadlines and events.
     */
    public ShowCommand(LocalDate date) {
        assert date != null : "Date must not be null";
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
        assertExecuteParams(taskList, ui, storage);
        LocalDate date = getDate();

        // Here are the deadlines / events on <date>:
        final String MESSAGE_SHOW_SUCCESS_PRE =
            Messages.HERE_ARE_THE + SPACE + DEADLINES_EVENTS_ON + SPACE + date + Constants.COLON;

        // Retrieve all tasks scheduled on the specified date
        // Sort the tasks by their LocalDateTime
        // https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
        TaskList scheduledTasks = taskList.getScheduledTasks(date);

        final String MESSAGE_EMPTY_LIST = "You have no" + SPACE + DEADLINES_EVENTS_ON + SPACE + date + Constants.DOT;
        ui.printTaskListMessage(scheduledTasks, MESSAGE_EMPTY_LIST, MESSAGE_SHOW_SUCCESS_PRE);
    }
}
