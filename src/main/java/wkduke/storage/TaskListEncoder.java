package wkduke.storage;

import wkduke.task.Task;
import wkduke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookEncoder.java
public class TaskListEncoder {
    public static List<String> encodeTaskList(TaskList taskList) {
        final List<String> encodedTasks = new ArrayList<>();

        for (Task task : taskList.getAllTask()) {
            encodedTasks.add(task.encode());
        }
        return encodedTasks;
    }
}
