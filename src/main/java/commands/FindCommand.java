package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.Constants;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + Constants.SPACE + Constants.OPEN_ANGLE_BRACKET + Constants.DESCRIPTION + Constants.CLOSE_ANGLE_BRACKET;
    public static final String MESSAGE_EMPTY_LIST = "There are no matching tasks in your list.";
    public static final String MESSAGE_SHOW_SUCCESS_PRE = "Here are the matching tasks in your list:";

    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        String description = getDescription();
        // System.out.println("Description is " + description);

        List<Task> tasksWithMatchingDescription = taskList.getAllTasksWithMatchingDescription(description);
        // TaskList tasksWithMatchingDescription = taskList.getAllTasksWithMatchingDescription(description);
        // System.out.println("tasksWithMatchingDescription are " + tasksWithMatchingDescription);

        // Check if there are no tasks on the specified date
        if (tasksWithMatchingDescription.isEmpty()) {
            String[] messageList = {MESSAGE_EMPTY_LIST};
            ui.printMessage(messageList);
            return;
        }

        // Prepare the message to display to the user
        ArrayList<String> messageList = new ArrayList<>(
                                            Arrays.asList(MESSAGE_SHOW_SUCCESS_PRE)
                                        );

        
        for (int i = 0; i < tasksWithMatchingDescription.size(); i++) {
            Task task = taskList.getTask(i);         // taskList.get(i) contains the checkbox
            String taskDescription = task.getDescription();
            if (taskDescription.contains(description)) {
                String index = Integer.toString(i + 1);
                String line = index + ". " + task;
                messageList.add(line);
            }
        }

        ui.printMessage(messageList);

        // public TaskList getAllTasksWithMatchingDescription(String description) {
        //     TaskList tasksWithMatchingDescription = new TaskList();
        //     for (int i = 0; i < tasksWithMatchingDescription.getSize(); i++) {
        //     // for (Task task : getTaskList()) {
        //         Task task = tasksWithMatchingDescription.getTask(i);
        //         String taskDescription = task.getDescription();
        //         if (taskDescription.contains(description)) {
        //             tasksWithMatchingDescription.addTask(task);
        //         }
        //     }
        //     return tasksWithMatchingDescription;
        // }

        // for (int i = 0; i < tasksWithMatchingDescription.size(); i++) {
        //     Task task = tasksWithMatchingDescription.get(i);
        //     // System.out.println(tasksWithMatchingDescription.get(i));
        // }
        // for (Task task : tasksWithMatchingDescription) {
        //     System.out.println(task.toString());
        // }

    }
    
}
