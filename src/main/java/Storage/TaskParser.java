package Storage;

import TaskManager.Deadline;
import TaskManager.Event;
import TaskManager.Task;
import TaskManager.Todo;
import UserUI.StarkException;

public class TaskParser {

    public static Task parseTasks(String taskSplit) throws StarkException.InvalidDescriptionException {

        String[] taskInfo = taskSplit.split(" ]");
        try {
            if (taskInfo.length == 3) {
                Task task = new Task("");
                if (taskInfo[0].trim().equalsIgnoreCase("D")) {
                    task = new Deadline(taskInfo[1], "");
                } else if (taskInfo[0].trim().equalsIgnoreCase("E")) {
                    task = new Event(taskInfo[1], "", "");
                } else if (taskInfo[0].trim().equalsIgnoreCase("T")) {
                    task = new Todo(taskInfo[1]);
                }
                if (taskInfo[2].trim().equalsIgnoreCase("X")) {
                    task.setStatus("mark");
                } else {
                    task.setStatus("unmark");
                }
                return task;
            }
        } catch (StarkException.InvalidDescriptionException e) {
            throw new StarkException.InvalidDescriptionException("Invalid task format found in the file");
        }


        return null;
    }
}
