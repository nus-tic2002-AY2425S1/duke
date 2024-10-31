package wkduke.storage;

import wkduke.common.Messages;
import wkduke.exception.FileContentException;
import wkduke.exception.TaskFormatException;
import wkduke.parser.TimeParser;
import wkduke.task.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Decodes task data from storage format into Task objects.
 * Supports decoding ToDo, Deadline, and Event tasks from encoded strings.
 */
//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java
public class TaskListDecoder {
    public static final Pattern TASK_PATTERN = Pattern.compile(
            "(?<taskType>[TDE]) \\| (?<taskStatus>[01]) \\| (?<description>[^|]+)"
                    + "(?: \\| (?<by>[^|]+))?"   // Optional by for D
                    + "(?: \\| (?<from>[^|]+) \\| (?<to>[^|]+))?" // Optional from and to for E
    );

    /**
     * Decodes a list of encoded task strings into a {@code TaskList}.
     *
     * @param encodedTaskList The list of encoded task strings.
     * @return A {@code TaskList} containing the decoded tasks.
     * @throws FileContentException If any encoded task has an invalid format.
     */
    public static TaskList decodeTaskList(List<String> encodedTaskList) throws FileContentException {
        TaskList taskList = new TaskList();
        for (String encodedTask : encodedTaskList) {
            taskList.addTask(decodeTaskFromString(encodedTask));
        }
        return taskList;
    }

    /**
     * Decodes a single encoded task string into a {@code Task} object.
     *
     * @param encodedTask The encoded task string.
     * @return A {@code Task} representing the decoded task.
     * @throws FileContentException If the encoded task has an invalid format.
     */
    public static Task decodeTaskFromString(String encodedTask) throws FileContentException {
        final Matcher matcher = TASK_PATTERN.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new FileContentException(
                    Messages.MESSAGE_INVALID_TASK_ENCODED_FORMAT,
                    String.format("EncodedTask='%s'", encodedTask)
            );
        }
        TaskType taskType = TaskType.fromCode(matcher.group("taskType"));
        int taskStatus = Integer.parseInt(matcher.group("taskStatus"));
        try {
            Task task = getTask(matcher, taskType);

            if (taskStatus == 1) {
                task.markAsDone();
            }
            return task;
        } catch (FileContentException e) {
            e.setDetail(String.format("EncodedTask='%s'", encodedTask));
            throw e;
        } catch (TaskFormatException e) {
            throw new FileContentException(
                    e.getMessage(),
                    String.format("EncodedTask='%s'", encodedTask),
                    e.getHelp()
            );
        }

    }

    /**
     * Creates a {@code Task} from matched data using the specified {@code TaskType}.
     *
     * @param matcher  The matcher containing the encoded task data.
     * @param taskType The type of the task (ToDo, Deadline, Event).
     * @return A {@code Task} representing the decoded task.
     * @throws FileContentException If required fields are missing or invalid for the task type.
     * @throws TaskFormatException  If there is an error parsing task date formats.
     */
    private static Task getTask(Matcher matcher, TaskType taskType) throws FileContentException, TaskFormatException {
        String description = matcher.group("description");
        Task task;
        switch (taskType) {
            case TODO -> task = new ToDo(description);
            case DEADLINE -> {
                String by = matcher.group("by");
                if (by == null) {
                    throw new FileContentException(
                            Messages.MESSAGE_INVALID_DEADLINE_ENCODED
                    );
                }
                LocalDateTime dateTime = TimeParser.parseDateTime(by);
                task = new Deadline(description, dateTime);
            }
            case EVENT -> {
                String from = matcher.group("from");
                String to = matcher.group("to");
                if (from == null || to == null) {
                    throw new FileContentException(
                            Messages.MESSAGE_INVALID_EVENT_ENCODED
                    );
                }
                LocalDateTime fromDateTime = TimeParser.parseDateTime(from);
                LocalDateTime toDateTime = TimeParser.parseDateTime(to);
                task = new Event(description, fromDateTime, toDateTime);
            }
            default -> throw new AssertionError("An invalid task type scenario is already handled earlier.");
        }
        return task;
    }
}
