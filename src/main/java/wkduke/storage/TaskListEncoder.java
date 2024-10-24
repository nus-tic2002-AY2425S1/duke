package wkduke.storage;

import wkduke.task.*;

import java.util.ArrayList;
import java.util.List;

//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookEncoder.java
public class TaskListEncoder {
    public static List<String> encodeTaskList(TaskList taskList) {
        final List<String> encodedTasks = new ArrayList<>();

        for (Task task : taskList.getAllTask()) {
            encodedTasks.add(encodeTaskToString(task));
        }
        return encodedTasks;
    }

    private static String encodeTaskToString(Task task) {
        final StringBuilder encodedTaskBuilder = new StringBuilder();

        // duke.task.Task type
        if (task instanceof ToDo) {
            encodedTaskBuilder.append("T");
        } else if (task instanceof Deadline) {
            encodedTaskBuilder.append("D");
        } else if (task instanceof Event) {
            encodedTaskBuilder.append("E");
        }

        // duke.task.Task status (0 for incomplete, 1 for complete)
        encodedTaskBuilder.append(" | ").append(task.isDone() ? "1" : "0");

        // duke.task.Task description
        encodedTaskBuilder.append(" | ").append(task.getDescription());

        // For duke.task.Deadline, append the "by" field (due date)
        if (task instanceof Deadline deadline) {
            encodedTaskBuilder.append(" | ").append(deadline.getBy());
        }

        // For duke.task.Event, append the "from" and "to" fields (start and end time)
        if (task instanceof Event event) {
            encodedTaskBuilder.append(" | ").append(event.getFrom())
                    .append(" | ").append(event.getTo());
        }
        return encodedTaskBuilder.toString();
    }
}
