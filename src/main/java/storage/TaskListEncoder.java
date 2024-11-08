package storage;
import java.util.List;

import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * The TaskListEncoder class is responsible for encoding a {@code TaskList} into a list of strings.
 * The list represents the tasks in a format that is suitable for storage, i.e. writing / saving to the tasks file.
 */
public class TaskListEncoder {

    /**
     * Encodes the given {@code TaskList} into a list of strings, where each string represents an encoded task.
     * 
     * @param taskList represents the {@code TaskList} to be encoded
     * @return a list of strings representing the encoded tasks
     */
    public static List<String> encodeTaskList(TaskList taskList) {
        final List<String> encodedTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            encodedTasks.add(encodeTaskToString(task));
        }
        return encodedTasks;
    }

    /**
     * Encodes a single {@code Task} into its string representation.
     * 
     * @param task represents the {@code Task} to be encoded
     * @return a string representation of the encoded {@code Task}
     */
    private static String encodeTaskToString(Task task) {
        return task.encodeTask();
    }

}
