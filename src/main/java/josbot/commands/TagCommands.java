package josbot.commands;

import josbot.JosBotException;
import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

public class TagCommands extends Command{

    public TagCommands(String commandType, String description) {
        super(commandType, description);
    }

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) throws JosBotException
    {
        String[] description_input = description.split(" ");
        Integer task_number = Integer.valueOf(description_input[0]) - 1;
        boolean tagged = false;
        if(commandType.equals("tag"))
        {
            tasks.getTasks().get(task_number).setTag(description_input[1].trim());
            tagged = true;
        }
        else if(commandType.equals("untag"))
        {
            tasks.getTasks().get(task_number).setTag("");
        }

        ui.showTagMessage(tasks.getTasks().get(task_number), tagged);
        file.saveToFile(tasks);
    }
}
