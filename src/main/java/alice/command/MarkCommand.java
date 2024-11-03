package alice.command;

import alice.storage.Storage;
import alice.task.*;
import alice.ui.Ui;

import java.io.IOException;

/**
 * <h1>Mark Command</h1>
 * The MarkCommand class implements an application that
 * allows the user to mark or unmark tasks in the tasklist
 * as done or not done.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class MarkCommand extends Command {

    public MarkCommand(int index, boolean isDone){
        super(index, isDone);
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task_mark = tasks.get(getIndex());
        task_mark.setDone(getDone());
        if (getDone()) {
            System.out.println("Nice! I've marked this task as done:)");
        } else {
            System.out.println("I've unmarked this task as done:)");
        }
        System.out.println(task_mark.print());
    }
}
