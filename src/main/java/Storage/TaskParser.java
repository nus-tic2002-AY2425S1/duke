package Storage;

import TaskManager.*;
import UserUI.StarkException;

import java.util.regex.PatternSyntaxException;

public class TaskParser {

    public static Task parseTasks(String taskSplit) throws StarkException.InvalidDescriptionException {

        try {
            String[] taskInfo = taskSplit.split(" ]");
            Task task = new Task("");

            if (taskInfo.length == 3) {

                if (taskInfo[0].trim().equalsIgnoreCase("D")) {
                    String[] tempSplit = taskInfo[1].split("\\(by:");
                    String description = tempSplit[0].trim();
                    String doneBy = tempSplit[1].replace(")", "").trim();
                    task = new Deadline(description, doneBy);
                } else if (taskInfo[0].trim().equalsIgnoreCase("E")) {
                    String[] tempSplit = taskInfo[1].split("\\(from:");
                    String description = tempSplit[0].trim();
                    String[] tempSplit2 = tempSplit[1].split(" to: ");
                    String startAt = tempSplit2[0].trim();
                    String endAt = tempSplit2[1].replace(")", "").trim();
                    task = new Event(description, startAt, endAt);
                } else if (taskInfo[0].trim().equalsIgnoreCase("T")) {
                    task = new Todo(taskInfo[1]);
                }
                if (taskInfo[2].trim().equalsIgnoreCase("X")) {
                    task.setStatus("mark");
                } else {
                    task.setStatus("unmark");
                }
            } else {
                throw new StarkException.InvalidDescriptionException("Task description is incorrect");
            }
            return task;
        } catch (IndexOutOfBoundsException |PatternSyntaxException e) {
            throw new StarkException.InvalidDescriptionException("Invalid task format found in the file");
        }
    }
}
