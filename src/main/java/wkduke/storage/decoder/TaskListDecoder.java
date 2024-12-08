package wkduke.storage.decoder;

import wkduke.exception.storage.FileContentException;
import wkduke.task.Task;
import wkduke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

import static wkduke.common.Messages.MESSAGE_DUPLICATE_TASK_IN_FILE;
import static wkduke.common.Messages.MESSAGE_DUPLICATE_TASK_IN_FILE_HELP;


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
        List<Task> tasks = new ArrayList<>();
        for (String encodedTask : encodedTasks) {
            Task task = TaskDecoder.decodeTask(encodedTask);
            if (tasks.contains(task)) {
                throw new FileContentException(MESSAGE_DUPLICATE_TASK_IN_FILE, String.format("EncodedTask='%s'", encodedTask),
                        MESSAGE_DUPLICATE_TASK_IN_FILE_HELP);
            }
            tasks.add(task);
        }
        return new TaskList(tasks);
    }
}
