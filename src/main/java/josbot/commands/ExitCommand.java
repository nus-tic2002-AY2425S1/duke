package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

/**
 * This Command is initialised when user wishes to exit the program
 */

public class ExitCommand extends Command {

    public ExitCommand(String commandType, String description) {
        super(commandType, description);
        this.isExit = true;
    }


    /**
     *
     * Sends a call to the ui class to generate the end greeting before the program closes.
     *
     * @param tasks A TaskList used to store the current tasks
     * @param ui A class to generate messages
     * @param file It stores the filepath and method related to storing and saving of the file
     */
    public void execute(TaskList tasks, UI ui, FileStorage file){
        ui.showGreeting("end");
    }
}
