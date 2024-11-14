package wkduke.storage.encoder;

import wkduke.task.Task;
import wkduke.task.TaskList;

import java.util.ArrayList;
import java.util.List;


/**
 * Encodes a {@code TaskList} into a format suitable for file storage.
 * Each task in the list is converted to a string representation.
 */
//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookEncoder.java
public class TaskListEncoder {
    /**
     * Encodes the tasks in the given {@code TaskList} into a list of strings.
     *
     * @param taskList The {@code TaskList} containing tasks to encode.
     * @return A {@code List<String>} where each string represents an encoded task.
     */
    public static List<String> encodeTaskList(TaskList taskList) {
        final List<String> encodedTasks = new ArrayList<>();

        for (Task task : taskList.getAllTask()) {
            encodedTasks.add(TaskEncoder.encodeTask(task));
        }
        return encodedTasks;
    }
}
