package alice.command;

import alice.storage.Storage;

import alice.task.Task;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

/**
 * <h1>Delete Command</h1>
 * The DeleteCommand class allows the user
 * to delete task(s) from the tasklist.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class DeleteCommand extends Command {

    public DeleteCommand(int index){
        super(index);
    }

    public DeleteCommand(String action, String instruction){
        super(action, instruction);
    }

    /**
     * This method is set to false as this command does not trigger the exit of the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (getIndex() == -1){
            System.out.println("Noted. I've removed these tasks:");
            tasks.remove(getInstruction());

        } else{
            assert getIndex() < tasks.size() : "index of arraylist shouldn't be bigger than the arraylist";
            Task task = tasks.get(getIndex());
            tasks.remove(getIndex());
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.print());
        }
        ui.showSize(tasks.size());
    }
}
