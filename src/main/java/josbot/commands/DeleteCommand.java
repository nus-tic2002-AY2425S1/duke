package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

public class DeleteCommand extends Command{

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file)
    {
        String[] delete_list = description.split(" ");
        try{

            int task_number = Integer.parseInt(delete_list[0].trim()) - 1;
            String task_message = tasks.getTasks().get(task_number).toString();
            tasks.removeTask(task_number);
            ui.showDeleteMessage(task_message, tasks.getTaskCount());
            file.saveToFile(tasks);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Error: There is no such list number available!");
        }
    }
}