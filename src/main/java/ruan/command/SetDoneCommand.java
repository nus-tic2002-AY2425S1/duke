package ruan.command;

import ruan.task.*;
import ruan.ui.*;
import ruan.storage.*;
import ruan.exception.*;

/**
 * Class combining of markDone & unmarkDone
 * Still one following Single Responsibility Principle
 * Only update isDone status
 */
public class SetDoneCommand extends Command {

    private int index;
    private boolean isDone;

    public SetDoneCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException {
        try {
            tasks.setDone(index, isDone);
            String acknowledgeString = isDone ? "Nice! " : "OK, ";
            String actionString = isDone ? "marked as done" : "marked as not done";
            String[] message = {
                acknowledgeString + "I've " + actionString + " the following task:",
                "  " + tasks.getTaskDescription(index)
            };
            ui.printMessage(message);

            storage.saveTasks(tasks.getTasks());

        } catch (IndexOutOfBoundsException e) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }
    }

}
