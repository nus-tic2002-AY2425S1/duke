package wkduke.storage.decoder;

import wkduke.exception.storage.FileContentException;
import wkduke.task.TaskList;

import java.util.List;


/**
 * Decodes a list of encoded task strings into a {@code TaskList} of {@code Task} objects.
 */
//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java
public class TaskListDecoder {
    /**
     * Decodes a list of encoded task strings into a {@code TaskList}.
     *
     * @param encodedTasks The list of encoded task strings.
     * @return A {@code TaskList} containing the decoded tasks.
     * @throws FileContentException If any encoded task has an invalid format.
     */
    public static TaskList decodeTaskList(List<String> encodedTasks) throws FileContentException {
        TaskList taskList = new TaskList();
        for (String encodedTask : encodedTasks) {
            taskList.addTask(TaskDecoder.decodeTask(encodedTask));
        }
        return taskList;
    }
}
