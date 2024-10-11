package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import java.io.IOException;

public class DeleteCommand extends Command {
    private final String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task deletedTask = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.showTaskDeleted(deletedTask, tasks.size());
            storage.saveTasks(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DennyException("Invalid task number.");
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}
