package josbot.commands;

import josbot.JosBotException;
import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

/**
 * This Command is initialised when user executes the 'tag' or 'untag' command
 */

public class TagCommands extends Command {

    public TagCommands(String commandType, String description) {
        super(commandType, description);
    }

    /**
     * Execute command for 'tag or 'untag'
     * It will tag the task that user have selected
     * based on the tag message that they've provided
     *
     * @param tasks A TaskList used to store the current tasks
     * @param ui    A class to generate messages
     * @param file  It stores the filepath and method related to storing and saving of the file
     * @throws JosBotException
     */
    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) {
        String[] descriptionInput = description.split(" ");
        Integer task_number = Integer.valueOf(descriptionInput[0]) - 1;
        boolean isTagged = false;
        if (commandType.equals("tag")) {
            tasks.getTasks().get(task_number).setTag(descriptionInput[1].trim());
            isTagged = true;
        } else if (commandType.equals("untag")) {
            tasks.getTasks().get(task_number).setTag("");
        }
        ui.showTagMessage(tasks.getTasks().get(task_number), isTagged);
        file.saveToFile(tasks);
    }
}
