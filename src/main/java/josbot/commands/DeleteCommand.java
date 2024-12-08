package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

/**
 * This Command is initialised when user executes the 'delete' command
 */

public class DeleteCommand extends Command{

    public DeleteCommand(String commandType, String description) {
        super(commandType, description);
    }


    /**
     *
     * Execute the 'delete' command
     * This method is used to delete the list that user have chosen based on
     * the number they've input in.
     *
     * @param tasks A TaskList used to store the current tasks
     * @param ui A class to generate messages
     * @param file It stores the filepath and method related to storing and saving of the file
     * @throws IndexOutOfBoundsException when a number received is not on the list
     * @throws NumberFormatException when a number is exepected but a string was received
     */
    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) throws IndexOutOfBoundsException, NumberFormatException{
      String[] deleteList = description.split(" ");
      int[] deleteIds = new int[deleteList.length];

      //To check if there is any out of bound number/string on all the number listed by user
      for(int x=0;x<deleteIds.length;x++){
          int taskNumber = Integer.parseInt(deleteList[x].trim()) - 1;
          tasks.getTasks().get(taskNumber);
          deleteIds[x] = taskNumber;
      }

      ui.showDeleteMessage("start", 0);
      for(int x=0;x<deleteIds.length;x++){
          String taskMessage = tasks.getTasks().get(deleteIds[x]-x).toString();
          tasks.removeTask(deleteIds[x]-x);
          ui.showMessage(taskMessage);
      }
      ui.showDeleteMessage("end", tasks.getTaskCount());
      file.saveToFile(tasks);
    }
}