package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

public class DeleteCommand extends Command{

    public DeleteCommand(String commandType, String description) {
        super(commandType, description);
    }

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) throws IndexOutOfBoundsException, NumberFormatException
    {
      String[] delete_list = description.split(" ");
      int[] delete_ids = new int[delete_list.length];

      //To check if there is any out of bound number/string on all the number listed by user
      for(int x=0;x<delete_ids.length;x++)
      {
          int task_number = Integer.parseInt(delete_list[x].trim()) - 1;
          tasks.getTasks().get(task_number);
          delete_ids[x] = task_number;
      }

      ui.showDeleteMessage("start", 0);
      for(int x=0;x<delete_ids.length;x++)
      {
          String task_message = tasks.getTasks().get(delete_ids[x]-x).toString();
          tasks.removeTask(delete_ids[x]-x);
          ui.showMessage(task_message);
      }
      ui.showDeleteMessage("end", tasks.getTaskCount());
      file.saveToFile(tasks);
    }
}