package storage;
import java.util.List;

import task.Task;
import task.TaskList;

import java.util.ArrayList;

public class TaskListEncoder {

    public static List<String> encodeTaskList(TaskList taskList) {
        final List<String> encodedTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            encodedTasks.add(encodeTaskToString(task));
        }
        return encodedTasks;
    }

    private static String encodeTaskToString(Task task) {
        return task.encodeTask();
    }

}
