package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import java.io.IOException;

public class UnmarkCommand extends Command {
    private final String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task task = tasks.getTask(index);
            task.markAsNotDone();
            ui.showTaskUnmarked(task);
            storage.saveTasks(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DennyException("Invalid task number.");
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}
