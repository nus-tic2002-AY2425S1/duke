package wkduke.command;

import wkduke.common.Messages;
import wkduke.exception.CommandOperationException;
import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {taskNumber}";
    public static final String MESSAGE_SUCCESS = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_FAILED = "This task is not yet marked as done:";
    public static final String TASK_PLACEHOLDER = "  %s";
    protected final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException, CommandOperationException {
        try {
            int taskIndex = taskNumber - 1;
            Task task = taskList.getTask(taskIndex);
            boolean updated = taskList.unmarkTask(taskIndex);

            if (updated) {
                storage.save(taskList);
                ui.printMessages(
                        MESSAGE_SUCCESS,
                        String.format(TASK_PLACEHOLDER, task.toString())
                );
            } else {
                ui.printMessages(
                        MESSAGE_FAILED,
                        String.format(TASK_PLACEHOLDER, task.toString())
                );
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='unmark', TaskNumber='%s'", taskNumber)
            );
        }
    }
}
