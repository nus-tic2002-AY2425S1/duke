import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java
public class TaskListDecoder {
    public static final Pattern TASK_PATTERN = Pattern.compile(
            "(?<taskType>[TDE]) \\| (?<taskStatus>[01]) \\| (?<description>[^|]+)"
                    + "(?: \\| (?<by>[^|]+))?"   // Optional by for D
                    + "(?: \\| (?<from>[^|]+) \\| (?<to>[^|]+))?" // Optional from and to for E
    );

    public static List<Task> decodeTaskList(List<String> encodedTaskList) throws IllegalValueException, StorageOperationException {
        final List<Task> decodedTasks = new ArrayList<>();

        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return decodedTasks;
    }

    public static Task decodeTaskFromString(String encodedTask) throws IllegalValueException {
        final Matcher matcher = TASK_PATTERN.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new IllegalValueException("Unable to decode the task due to an invalid encoded format.");
        }

        TaskType taskType = TaskType.fromCode(matcher.group("taskType"));
        int taskStatus = Integer.parseInt(matcher.group("taskStatus"));
        Task task = getTask(matcher, taskType);

        if (taskStatus == 1) {
            task.markAsDone();
        }
        return task;
    }

    private static Task getTask(Matcher matcher, TaskType taskType) {
        String description = matcher.group("description");
        Task task;

        switch (taskType) {
            case TODO -> task = new ToDo(description);
            case DEADLINE -> {
                String by = matcher.group("by");
                task = new Deadline(description, by);
            }
            case EVENT -> {
                String from = matcher.group("from");
                String to = matcher.group("to");
                task = new Event(description, from, to);
            }
            default -> throw new AssertionError("An invalid task type scenario is already handled earlier.");
        }
        return task;
    }
}
