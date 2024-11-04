package commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.Messages;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <date>";
    // public static final String MESSAGE_DELETE_SUCCESS_PRE = "Noted. I've removed this task:";
    public static final String MESSAGE_EMPTY_LIST = "You have no deadlines / events on ";
    public static final String MESSAGE_SHOW_SUCCESS_PRE = "Here are the deadlines / events on ";

    protected LocalDate date;
    
    public ShowCommand(LocalDate date) {
        this.date = date;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    // Create a List<Task> for the deadline/events that are scheuled on a specific date, similar to "list" cmd maybe
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        LocalDate date = getDate();

        List<Task> tasksOnDate = taskList.getAllTasksOnDate(date);
        
        if (tasksOnDate.isEmpty()) {
            String[] messageList = {MESSAGE_EMPTY_LIST + date};
            ui.printMessage(messageList);
            return;
        }

        int taskListSize = taskList.getSize();
        ArrayList<String> messageList = new ArrayList<>(
                                            Arrays.asList(
                                                String.format("%s%s:", MESSAGE_SHOW_SUCCESS_PRE, date)
                                            )
                                        );

        for (int i = 0; i < taskListSize; i++) {
            Task current = taskList.getTask(i);         // taskList.get(i) contains the checkbox
            if (current.isOnDate(date)) {
                String index = Integer.toString(i + 1);
                String line = index + ". " + current;
                messageList.add(line);
            }
        }
        
        ui.printMessage(messageList);
    }
}
