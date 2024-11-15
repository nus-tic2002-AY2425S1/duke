package storage;

import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Encodes a {@code TaskList} into a list of strings.
 * The list represents the tasks in a format that is suitable for storage, i.e. writing / saving to the tasks file.
 */
public class TaskListEncoder {

    // Add a private constructor to hide the implicit public one.
    private TaskListEncoder() {

    }

    /**
     * Encodes the given {@code TaskList} into a list of strings, where each string represents an encoded task.
     *
     * @param taskList represents the {@code TaskList} to be encoded.
     * @return a list of strings representing the encoded tasks.
     */
    protected static List<String> encodeTaskList(TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        final List<String> encodedTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            assert task != null : "Task in the list should not be null";
            encodedTasks.add(encodeTaskToString(task));
        }
        return encodedTasks;
    }

    /**
     * Encodes a single {@code Task} into its string representation.
     *
     * @param task represents the {@code Task} to be encoded.
     * @return a string representation of the encoded {@code Task}.
     */
    protected static String encodeTaskToString(Task task) {
        assert task != null : "Task should not be null";
        String encodedTask = task.encodeTask();
        assert encodedTask != null && !encodedTask.isEmpty() : "Encoded task should not be null or empty";
        return encodedTask;
    }

}
