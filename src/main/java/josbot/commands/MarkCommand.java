package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

/**
 * This Command is initialised when user executes the 'mark' or 'unmark' command
 */

public class MarkCommand extends Command {

    public MarkCommand(String commandType, String description) {
        super(commandType, description);
    }

    /**
     * Execute Command for 'mark' or 'unmark'.
     * This method will mark or unmark as done the tasks number
     * provided by the user
     *
     * @param tasks A TaskList used to store the current tasks
     * @param ui    A class to generate messages
     * @param file  It stores the filepath and method related to storing and saving of the file
     */

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) {
        Integer taskNumber = Integer.valueOf(description) - 1;
        boolean isMarked = false;
        if (commandType.equals("mark")) {
            tasks.getTasks().get(taskNumber).markAsDone();
            isMarked = true;
        } else if (commandType.equals("unmark")) {
            tasks.getTasks().get(taskNumber).markAsNotDone();
        }

        ui.showMarkMessage(tasks.getTasks().get(taskNumber), isMarked);
        file.saveToFile(tasks);
    }
}
