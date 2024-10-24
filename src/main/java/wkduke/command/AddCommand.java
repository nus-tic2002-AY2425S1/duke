package wkduke.command;

import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD_TODO = "todo";
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String MESSAGE_SUCCESS_PRE = "Got it. I've added this task:";
    public static final String TASK_PLACEHOLDER = "  %s";
    public static final String MESSAGE_SUCCESS_POST = "Now you have %s tasks in the list.";
    protected Task task;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        taskList.addTask(task);
        storage.save(taskList);
        ui.printMessages(
                MESSAGE_SUCCESS_PRE,
                String.format(TASK_PLACEHOLDER, task.toString()),
                String.format(MESSAGE_SUCCESS_POST, taskList.size())
        );
    }
}
